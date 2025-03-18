import java.util.Arrays;
public class MergeSortedArrays {
    public static int[]
mergeSortedArrays(int[] arr1,int[] arr2){
    int n1=arr1.length,n2=arr2.length;
    int[] mergedArray = new int[n1+n2];
      int i=0,j=0,k=0;
       
      while(i < n1 && j < n2){
         if(arr1[i]<=arr2[j]){
            mergedArray[k++]=arr1[i++];
         }
         else{
           mergedArray[k++]=arr2[j++];
         }
      }
      while(j < n2){
        mergedArray[k++]=arr2[j++];
      }

      return mergedArray;
}

public static void main(String[]args){
    int[] arr1={1,3,5,7};
    int[] arr2={2,4,6,8};

    int[] mergedArray=mergeSortedArrays(arr1,arr2);
      System.out.println("Merged Sorted Array:"+ Arrays.toString(mergedArray));
}

}

