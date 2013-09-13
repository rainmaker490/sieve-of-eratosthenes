package homework_3_467;
// Import Math Library (Contains built in Big Integer Class)
import java.math.*;
import java.util.*;

/**
 * @author Varun Patel
 */
public class Homework_3_467 {
    // declare protected variables i, p, and j
    protected static BigInteger i;
    protected static BigInteger p = new BigInteger("2");
    protected static BigInteger j;
    private static BigInteger numberOfPrimes = new BigInteger("0");
    private static double log;
    private static BigInteger temp;
    private static final BigInteger TWO = new BigInteger("2");
    private static int counter = 0;
    private static BigInteger previousPrime = BigInteger.ZERO;
    
    // main program - starting point of the program
    public static void main(String[] args) {   
        printSieve(sieveOfEratosthenes(new BigInteger("4000")), 
                new BigInteger("2000"));
        
        System.out.println();
        System.out.println();
        
        
        printTrialDivision(trialDivision(new BigInteger("400009"),
                new BigInteger("400009")));
        
        printTrialDivision(trialDivision(new BigInteger("18279971"),
                new BigInteger("18279971")));

        printTrialDivision(trialDivision(new BigInteger("198765432269"),
                new BigInteger("198765432269")));        
                
    }
    
    // printSieve function that prints array returned by sieveOfErathosthenes
    public static void printSieve(Map<BigInteger, BigInteger> h, BigInteger 
            lowerBound){
        for (BigInteger bgt : h.values()){
            // printing the primes
            if ((bgt != null) && (bgt.compareTo(lowerBound) > 0)){
                System.out.print(bgt.intValue() + " " );
                
                // check to see if there primes are consecutive
                if((bgt.subtract(previousPrime)).compareTo(BigInteger.
                        valueOf(2)) == 0){
                    counter++;
                }
                // reassign the value of previousPrime
                previousPrime = bgt;
            }
        }
        
        // extra lines to separate out the previous list of primes from the
        // next results (for a clear display of results.
        System.out.println();
        System.out.println();
        
        // display the results
        System.out.println("There are a total of " + counter + " pairs of prime "
                + "numbers that differ by 2");
    }
    
    // sieveOfErathosthenes function that returns a List h of primes
    public static Map<BigInteger, BigInteger> sieveOfEratosthenes (BigInteger 
            upperBound){
        
        /* Algorithm 5 */
        Map<BigInteger, BigInteger> l;
        l = new TreeMap<BigInteger, BigInteger>();
        
        // Set values in TreeMap index l to one
        for (i = new BigInteger("2"); (i.compareTo(upperBound) <= 0) ; 
                i = i.add(BigInteger.ONE)){
            l.put(i,BigInteger.ONE);
        }
        
        while ((p.multiply(p)).compareTo(upperBound) <= 0){
            j = p.add(p);
            
            while (j.compareTo(upperBound) <= 0){
                l.put(j, BigInteger.ZERO);
                j = j.add(p);
            }
            
            p = p.add(BigInteger.ONE);
            
            while (l.get(p).compareTo(BigInteger.ZERO) == 0){
                p = p.add(BigInteger.ONE);
            }
        }
        
        Map<BigInteger, BigInteger> h;
        h = new TreeMap<BigInteger, BigInteger>();
        h.put(BigInteger.ONE, BigInteger.valueOf(2));
        j = BigInteger.valueOf(2);
        
        for (p = BigInteger.valueOf(3); (p.compareTo(upperBound) <= 0); p = 
                p.add(BigInteger.ONE)){
            if (l.get(p).compareTo(BigInteger.ONE) == 0){
                h.put(j, p);
            }
            j = j.add(l.get(p));         
        }        
        return h;
    }
    
    /* Algorithm 6 */
    public static BigInteger div(BigInteger n, BigInteger d, int subscript){
        BigInteger e = BigInteger.ZERO;
        BigInteger f = n;
        
        while (f.mod(d).equals(BigInteger.ZERO)){
            f = f.divide(d);
            e = e.add(BigInteger.ONE);
        }
        
        if(subscript == 1){
            return e;
        } else if (subscript == 2){
            return f;
        } else {
            return BigInteger.valueOf(-1);
        }
        
    }
    
    /* Algorithm 7 */
    public static List<Object> trialDivision(BigInteger n, BigInteger max){
        
        List<Object> output = new ArrayList<Object>();
        BigInteger i = new BigInteger("0");
        BigInteger f = n;
        BigInteger d = new BigInteger("2");
        Map<BigInteger, BigInteger> p;
        p = new TreeMap<BigInteger, BigInteger>();
        Map<BigInteger, BigInteger> e;
        e = new TreeMap<BigInteger, BigInteger>();
        
        /* Consider d = 2 separately */
        if (f.mod(d).equals(BigInteger.ZERO)){
            i = i.add(BigInteger.ONE);
            p.put(i, d);
            e.put(i, div(f,d,1));
            f = div(f,d,2);
        }
        
        d = BigInteger.valueOf(3);
        while ((d.compareTo(max) <= 0) && (d.multiply(d)).compareTo(f) <= 0){
            if(f.mod(d).equals(BigInteger.ZERO)){
                i = i.add(BigInteger.ONE);
                p.put(i, d);
                e.put(i, div(f,d,1));
                f = div(f,d,2);
            }
            d = d.add(TWO);
        }
        
        if ((f.compareTo(BigInteger.ONE) > 0) && ((d.multiply(d))
                .compareTo(f) > 0)){
            
            i = i.add(BigInteger.ONE);
            p.put(i, f);
            e.put(i, BigInteger.ONE);
            f = BigInteger.ONE;
        }
        // Add results to output list
        output.add(i);
        output.add(e);
        output.add(p);
        output.add(f);
        return output;
    }
    
    // print Trial Division Funcion
    public static void printTrialDivision(List<Object> output){
        System.out.println("i: " + output.get(0));
        System.out.print("e: ");
        printMaps((TreeMap<BigInteger, BigInteger>)output.get(1));
        System.out.print("p: ");
        printMaps((TreeMap<BigInteger, BigInteger>)output.get(2));
        System.out.println("f: " + output.get(3));
    }
    
    // Print Map function
    public static void printMaps(TreeMap<BigInteger, BigInteger> maps){
        for (Object bgt : maps.values()){
            System.out.print(bgt + " ");
        }
    }
}