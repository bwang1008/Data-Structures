import java.util.Arrays;
public class SegmentTree {
    //Credits to AI Cash!
    int n; //length
    int[] arr;
    
    public SegmentTree(int[] a) {
        n = a.length;
        arr = new int[n << 1];
        
        for(int i = 0; i < n; i++)
            arr[i+n] = a[i];
        for(int i = n-1; i >= 0; i--)
            arr[i] = arr[i << 1] + arr[i << 1 | 1];
    }
    
    public int get(int pos) {
        return arr[pos + n];
    }
    
    public void modify(int pos, int val) {
        for(arr[pos += n] = val; pos > 1; pos >>= 1)
            arr[pos >> 1] = arr[pos] + arr[pos ^ 1];
    }
    
    public long sum(int left, int right) {
        long sum = 0;
        for(left += n, right += n; left < right; left >>= 1, right >>= 1) {
            if((left & 1) == 1) sum += arr[left++];
            if((right & 1) == 1) sum += arr[--right];
        }
        
        return sum;
    }
    
    public static void main(String[] args) {
        int[] a = new int[] {-3, 5, 8, 2, 1, 1, 0, 0, 5};
        SegmentTree st = new SegmentTree(a);
        System.out.println(st.sum(0, 9));
        System.out.println(Arrays.toString(st.arr));
        System.out.println(st.sum(1, 4));
        st.modify(3, 0);
        System.out.println(st.sum(1, 4));
        System.out.println(Arrays.toString(st.arr));
        System.out.println(st.get(3));
    }

}