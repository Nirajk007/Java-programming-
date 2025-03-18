import java.util.Arrays;

public class AlphabeticalOrder {
    public static void main(String[] args) {
        String[] names={"Zara","Niraj","Chintamani","Atharv","Varad","Saharsh","Pradnesh","Shrikant","Sumit","Bhakti"};

        Arrays.sort(names);

        System.out.println("Names sorted in Alphabetical Order are:");
        for(String name:names){
            System.out.println(name);
        }
    }
}
