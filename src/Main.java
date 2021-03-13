
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    
    public static final int MAXBOOKSPRICE=1000001;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
       
        String lineBooks = br.readLine(); 
        int maxBooks=Integer.parseInt(lineBooks);
        
        String booksPrice = br.readLine(); 
        String[] booksPriceParts=booksPrice.split(" ");
        int[] priceBooks=new int[maxBooks];
        
        String peterMoney = br.readLine();
        int money=Integer.parseInt(peterMoney);
        
        if(maxBooks>=2 && maxBooks<=10000){
            for(int i=0;i<maxBooks;i++){
                priceBooks[i]=Integer.parseInt(booksPriceParts[i]);
            }
        }
        
        insertionSort(priceBooks);
        int[] prices=searchBestPrices(priceBooks,money);
        
        bw.write("Peter should buy books whose prices are "+prices[0]+" and "+prices[1]);
        bw.newLine();
        
        br.close();
        bw.close();
    }
    
    public static int[] insertionSort(int[] arrayPrices){
        for(int i=1;i<arrayPrices.length;i++){
            for(int j=i;j>0 && arrayPrices[j-1]>arrayPrices[j];j--){
                int temp=arrayPrices[j];
                arrayPrices[j]=arrayPrices[j-1];
                arrayPrices[j-1]=temp;
            }
        }
        return arrayPrices;
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
        int minValue=Integer.MAX_VALUE;
        
        for(int i=0;i<priceBooks.length;i++){
            int mainSubtraction=peterPrice-priceBooks[i];
            int foundBestPrices=binarySearch(priceBooks,mainSubtraction);
            int finalSubtraction=Math.abs(priceBooks[i]-mainSubtraction);
            
            if(i!=foundBestPrices && foundBestPrices>=0 && finalSubtraction<minValue && finalSubtraction<MAXBOOKSPRICE){
                minValue=finalSubtraction;
                finalPrices[0]=priceBooks[i];
                finalPrices[1]=mainSubtraction;
            }
        }
        insertionSort(finalPrices);
        return finalPrices;
    }
}
