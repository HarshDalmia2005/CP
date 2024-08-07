
import java.util.*;

public class LZ {

    static class LazySGTree {

        int seg[];
        int lazy[];

        public LazySGTree(int n) {
            seg = new int[4 * n + 1];
            lazy = new int[4 * n + 1];
        }

        public void build(int ind, int low, int high, int arr[]) {
            if (low == high) {
                seg[ind] = arr[low];
                return;
            }

            int mid = (low + high) / 2;
            build(2 * ind + 1, low, mid, arr);
            build(2 * ind + 2, mid + 1, high, arr);
            seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
        }

        public int query(int ind, int low, int high, int l, int r) {

            if (lazy[ind] != 0) {
                seg[ind] += (high - low + 1) * lazy[ind];

                if (low != high) {
                    lazy[2 * ind + 1] += lazy[ind];
                    lazy[2 * ind + 2] += lazy[ind];
                }
                lazy[ind] = 0;
            }

            if (r < low || l > high) {
                return 0;
            }

            if (low >= l && r <= high) {
                return seg[ind];
            }

            int mid = (low + high) >> 1;
            int left = query(2 * ind + 1, low, mid, l, r);
            int right = query(2 * ind + 2, mid + 1, high, l, r);

            return left + right;
        }

        public void update(int ind, int low, int high, int l, int r, int val) {

            if (lazy[ind] != 0) {
                seg[ind] += (high - low + 1) * val;

                if (low != high) {
                    lazy[2 * ind + 1] += lazy[ind];
                    lazy[2 * ind + 2] += lazy[ind];
                }
                lazy[ind] = 0;
            }

            if (r < low || l > high) {
                return;
            }

            if (low >= l && r <= high) {
                seg[ind] += (high - low + 1) * val;

                if (low != high) {
                    lazy[2 * ind + 1] += lazy[ind];
                    lazy[2 * ind + 2] += lazy[ind];
                }
                return;
            }

            int mid = (low + high) >> 1;

            update(2 * ind + 1, low, mid, l, r, val);
            update(2 * ind + 2, mid + 1, high, l, r, val);

            seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LazySGTree Lz = new LazySGTree(n);
    }

}
