package indi.zhangzqit.yunnanshop.util;

/**
 * �Զ��庯��: ���еķ�����Ϊ��̬����
 */
public class MyFunction {

	public static boolean contains(int rid, int[] myRid) {
		for (int my : myRid) {
			// ��ǰ����Աӵ�е�ǰ��ɫ
			if (my == rid) {
				return true;
			}
		}
		return false;
	}
}
