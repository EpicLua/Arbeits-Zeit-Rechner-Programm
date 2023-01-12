package window.rechner;

public class Rechner {
    String begin;
    String ende;
    int[] houer;
    int[] min;
    String[] Value;
    int[] ref;


public Rechner(String B, String E)
    {
        this.begin = B;
        this.ende = E;
        this.Value = new String[4];
        this.Value[0] = B;
        this.Value[1] = E;
        this.to_time();
    }
    private void to_time()
    {
        this.houer = new int[2];
        this.min = new int[2];

        int tmp ;
        if(begin.length() == 5)
        {
            tmp = this.begin.charAt(0) - '0';
            this.houer[0] = tmp * 10 + this.begin.charAt(1)-'0';
            tmp = this.begin.charAt(3) -'0';
            this.min[0] = tmp * 10 + this.begin.charAt(4) - '0';

        }
        else  if(begin.length()== 4)
        {
            this.houer[0] =  this.begin.charAt(0)-'0';
            tmp = this.begin.charAt(2) -'0';
            this.min[0] = tmp * 10 + this.begin.charAt(3) - '0';
        }

        if(ende.length() == 5)
        {
            tmp = this.ende.charAt(0) - '0';
            this.houer[1] = tmp * 10 + this.ende.charAt(1)-'0';
            tmp = this.ende.charAt(3) -'0';
            this.min[1] = tmp * 10 + this.ende.charAt(4) - '0';

        }
        else  if(begin.length()== 4)
        {
            this.houer[0] =  this.begin.charAt(0)-'0';
            tmp = this.begin.charAt(2) -'0';
            this.min[0] = tmp * 10 + this.begin.charAt(3) - '0';
        }

        this.rech();
    }
    private void rech()
    {
        int tmp_h = 0;
        int tmp_m = 0;
        if(this.min[1]< this.min[0])
        {
            tmp_h = this.houer[1] - 1 -this.houer[0];
            tmp_m = this.min[1] - this.min[0] + 60;
        }
        else
        {
            tmp_h = this.houer[1]  -this.houer[0];
            tmp_m = this.min[1] - this.min[0] ;
        }
        ref = new int[2];
        ref[0] = tmp_h;
        ref[1] = tmp_m;

        if(tmp_m < 10)
        {
            this.Value[2] = Integer.toString(tmp_h) + ":0" + Integer.toString(tmp_m);
        }
        else
        {
            this.Value[2] = Integer.toString(tmp_h) + ":" + Integer.toString(tmp_m);
        }

        tmp_m = tmp_m * 5 / 3;

        if(tmp_m < 10)
        {
            this.Value[3] = Integer.toString(tmp_h) + ":0" + Integer.toString(tmp_m);
        }
        else
        {
            this.Value[3] = Integer.toString(tmp_h) + ":" + Integer.toString(tmp_m);
        }

    }
    public String get_Value(int index)
    {
        return this.Value[index];
    }
    public int[] get_Time()
    {

        return ref;
    }
}
