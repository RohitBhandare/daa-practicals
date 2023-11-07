package DAA_Practicals;

public class Permutations {

    public static void main(String[] args) {
        
        String str="ABC";

        permute("",str);
    }

    private static void permute(String p,String up) {

        if(up.isEmpty()){
            System.out.println(p);
            return;
        }

        char ch=up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {

            permute(p.substring(0,i)+ch+p.substring(i,p.length()), up.substring(1));
        }
      
    }
    
}
