import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    
    public static final int MAXBOOKSPRICE=1000001;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        
        String finalResult="";
        String lineBooks=br.readLine();
        
        while(lineBooks!=null){
            int maxBooks=Integer.parseInt(lineBooks);
            int[] priceBooks=new int[maxBooks];
            String[] booksPriceParts=br.readLine().split(" ");
            
            for(int i=0;i<booksPriceParts.length;i++){
                priceBooks[i]=Integer.parseInt(booksPriceParts[i]);
            }
            
            int money=Integer.parseInt(br.readLine());
            Arrays.sort(priceBooks);
            int[] prices=searchBestPrices(priceBooks,money);
           
           finalResult+="Peter should buy books whose prices are "+prices[0]+" and "+prices[1]+"\n\n";
           lineBooks=br.readLine();
           lineBooks=br.readLine();
        }
        bw.write(finalResult);
        br.close();
        bw.close();
    }
    
    public static int binarySearch(int[] bestpPriceBooks,int mainSubtraction){
        int i=0;
        int j=bestpPriceBooks.length-1;
        int pos=-1;
        while(i<=j && pos<0){
            int m=(i+j)/2;
            if(bestpPriceBooks[m]==mainSubtraction){
                pos=m;
            }
            else if(bestpPriceBooks[m]>mainSubtraction){
                j=m-1;
            }
            else{
                i=m+1;
            }
        }
        return pos;
    }
    
    public static int[] searchBestPrices(int[] priceBooks,int peterPrice){
        int[] finalPrices=new int[2];
        int initialValue=Integer.MAX_VALUE;
        for(int i=0;i<priceBooks.length;i++){
            int mainSubtraction=peterPrice-priceBooks[i];
            int foundBestPrices=binarySearch(priceBooks,mainSubtraction);
            int finalSubtraction=Math.abs(priceBooks[i]-mainSubtraction);
            
            if(i!=foundBestPrices && foundBestPrices>=0 && finalSubtraction<initialValue && finalSubtraction<MAXBOOKSPRICE){
                initialValue=finalSubtraction;
                finalPrices[0]=priceBooks[i];
                finalPrices[1]=mainSubtraction;
            }
        }
        Arrays.sort(finalPrices);
        return finalPrices;
    }
}
