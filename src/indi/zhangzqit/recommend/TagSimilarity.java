package indi.zhangzqit.recommend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.Partitioner;

public class TagSimilarity {
	public static class TagSimilarityMapper extends MapReduceBase implements
			Mapper<Text, Text, LongWritable, Text> {

		private int bucketCount;
		private long hash;
		private LongWritable keyHolder = new LongWritable();
		private Text valueHolder = new Text();
		private int userCount = 0;
		private int questionCount = 0;

		public void configure(JobConf job) {
			String bucketCountStr = job.get("bucketCount");
			bucketCount = Integer.parseInt(bucketCountStr);
		}

		public void close() {
			System.out.println("User: " + userCount + " Questions: "
					+ questionCount);
		}

		public void map(Text key, Text value,
				OutputCollector<LongWritable, Text> out, Reporter reporter)
				throws IOException {

			String[] keyStr = key.toString().split(",");

			hash = keyStr[1].hashCode() % bucketCount;
			hash = hash < 0 ? -hash : hash;

			valueHolder.set(key.toString() + "," + value.toString());

			if (keyStr[0].equals("U")) {
				for (int i = 0; i < bucketCount; ++i) {
					keyHolder.set((hash * bucketCount + i) * 10);
					out.collect(keyHolder, valueHolder);
				}
				userCount++;
			} else {
				for (int i = 0; i < bucketCount; ++i) {
					keyHolder.set(((i * bucketCount + hash) * 10) + 1);
					out.collect(keyHolder, valueHolder);
				}
				questionCount++;
			}
		}

	}

	public static class TagSimilarityReducer extends MapReduceBase implements
			Reducer<LongWritable, Text, NullWritable, Text> {

		private Text valueHolder = new Text();
		private List<String> userTypeValues = new ArrayList<String>();
		private String userId;
		private String questionId;
		private double sim;
		private boolean userType;
		private String idTagsValue;
		private String[] idTagsSplit;
		private int userCount = 0;
		private int questionCount = 0;
		private int simCount = 0;

		public void close() {
			System.out.println("Reducer: User: " + userCount + " Questions: "
					+ questionCount + " Similarity Count: " + simCount);
		}

		public void reduce(LongWritable key, Iterator<Text> values,
				OutputCollector<NullWritable, Text> out, Reporter reporter)
				throws IOException {

			userTypeValues.clear();

			while (values.hasNext()) {
				Text value = values.next();

				userType = value.toString().startsWith("U");
				idTagsValue = value.toString().substring(2);

				if (userType) {
					userTypeValues.add(idTagsValue);
					userCount++;
				} else {
					String questionStr = idTagsValue;
					idTagsSplit = questionStr.split(",");
					questionId = idTagsSplit[0];
					questionCount++;

					for (String userStr : userTypeValues) {

						sim = findSimilarity(userStr, questionStr);
						simCount++;
						idTagsSplit = userStr.split(",");
						userId = idTagsSplit[0];

						if (sim != 1.0) {
							valueHolder.set(userId + "," + questionId + ","
									+ sim);
							out.collect(NullWritable.get(), valueHolder);
						}
					}

				}
			}
		}

		public double findSimilarity(String users, String questions) {

			double distance = 1.0;
			int position = users.indexOf(",");
			users = users.substring(position + 1);
			position = questions.indexOf(",");
			questions = questions.substring(position + 1);

			String[] srcTerms = users.split(",");
			String[] trgTerms = questions.split(",");

			int matchCount = 0;
			for (String srcTerm : srcTerms) {
				for (String trgTerm : trgTerms) {
					if (srcTerm.equals(trgTerm)) {
						++matchCount;
					}
				}
			}

			int srcNonMatchCount = srcTerms.length - matchCount;
			int trgNonMatchCount = trgTerms.length - matchCount;
			distance = 1.0
					- (double) matchCount
					/ ((double) matchCount + 0.8 * srcNonMatchCount + 0.2 * trgNonMatchCount);
			return distance;
		}

	}

	public static class IdPairPartitioner implements
			Partitioner<LongWritable, Text> {
		public int getPartition(LongWritable key, Text value, int numPartitions) {
			int keyVal = (int) (key.get() / 10);
			return keyVal % numPartitions;
		}

		public void configure(JobConf jobConf) {

		}

	}

	public static class IdPairGroupComprator extends WritableComparator {
		private static final int KEY_EXTENSION_SCALE = 10;

		protected IdPairGroupComprator() {
			super(LongWritable.class, true);
		}

		public int compare(WritableComparable w1, WritableComparable w2) {
			Long t1 = ((LongWritable) w1).get() / KEY_EXTENSION_SCALE;
			Long t2 = ((LongWritable) w2).get() / KEY_EXTENSION_SCALE;

			int comp = t1.compareTo(t2);
			return comp;
		}
	}

}
