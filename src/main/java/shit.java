/**
 * Created by 803100 on 15.11.2016.
 */
public class shit {
    private static int k;
    private static int b;
    private  int Num;
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
    private static int func (int N){
        int count=0;
        for (int kk=1; kk<=N;kk++){
            if (gcd(kk,N)==1){
                for (int bb=1; bb<kk; bb++){
                    //if ((14*kk+bb)%55==44 && (12*kk+bb)%55==52){
                    if ((31*kk+bb)%55==33 && (37*kk+bb)%55==17){
                        k=kk;
                        b=bb;
                        count++;
                        break;
                        //return count;
                    }
                }
            }

        }
        return count;
    }
        public static void main(String... args){
            System.out.println("Hello world");
            int n=func(55);
            //System.out.println(n);
           // System.out.println(k+" "+b);
            int y=13;
            int bb=45;
            int k0=41;
            int N=55;
            int x=(((y-bb)*k0)%N);
            int x1=52;
            int s=(40*x1 % N);
            System.out.println(s);
        }
}
