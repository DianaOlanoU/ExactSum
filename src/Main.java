
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    
    public static int[] priceBooks;
    public static final int MAXBOOKSPRICE=1000001;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        
        /**
         * The number of books.
         */
        String lineBooks = br.readLine(); 
        int maxBooks=Integer.parseInt(lineBooks);
        
        /**
         * The price of the number of books is read as well the split function will be used to obtain the numbers of the chain and it will be stored in the priceBooks variable.
         */
        String booksPrice = br.readLine(); 
        String[] booksPriceParts=booksPrice.split(" ");
        priceBooks=new int[maxBooks];
        
        /**
         * The amount of money Peter has.
         */
        String peterMoney = br.readLine();
        int money=Integer.parseInt(peterMoney);
        
        /**
         * The respective verifications are made in this way the program fulfills the limitations that the problem indicatesas as well the value of String is changed to Integer.
         */
        if(maxBooks>=2 && maxBooks<=10000){
            for(int i=0;i<maxBooks;i++){
                priceBooks[i]=Integer.parseInt(booksPriceParts[i]);
            }
        }
        
        /**
         * The respective verifications are made in this way the program fulfills the limitations that the problem indicates and as it we proceed to order the array of the prices of the books.
         */
        if(priceBooks.length-MAXBOOKSPRICE<MAXBOOKSPRICE){
            insertionSort();
        }
        
        /**
         * The binarySearch method is called to find which book prices fullfill the money that Peter has; the array of book prices and Peter's money is sent as a parameter.
         */
        bw.write(binarySearch(priceBooks,money));
        
        /**
         * We proceed to close the BufferedReader and the BufferedWriter.
         */
        br.close();
        bw.close();
    }
    
    /**
     * Method name: insertionSort.
     * The price array is ordered.
     */
    public static void insertionSort(){
        for(int i=1;i<priceBooks.length;i++){
            for(int j=i;j>0 && priceBooks[j-1]>priceBooks[j];j--){
                int temp=priceBooks[j];
                priceBooks[j]=priceBooks[j-1];
                priceBooks[j-1]=temp;
            }
        }
    }
    
    /**
     * Method name:binarySearch
     * @param priceBooks
     * @param peterPrice
     * It is evaluated which books Peter could buy with his money.
     * @return A message with the books prices that Peter can be bought.
     */
    public static String binarySearch(int[] priceBooks,int peterPrice){
        int i=0;
        int j=priceBooks.length-1;
        int auxBook1;
        int auxBook2;

        while(i<j){
            if(priceBooks[i]+priceBooks[j]==peterPrice){
                auxBook1=i++;
                auxBook2=j--;
            }

            else if(priceBooks[i]+priceBooks[j]>peterPrice){
                auxBook2=j--;
            }

            else{
                auxBook1=i++;
            }
        }
        return "Peter should buy books whose prices are "+priceBooks[i]+" and "+priceBooks[j];
    }
}
