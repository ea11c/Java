// class Fraction
// Author:  Bob Myers
// Editor:  Eric Adams
// For COP3252, Java Programming

public class Fraction
{
    private int numerator = 0;		// numerator (and keeps sign)
    private int denominator = 1;		// always stores positive value

    public Fraction()
    {
    }

    public Fraction(int n, int d)
    {
        if (set(n,d)==false)
            set(0,1);
    }

    public boolean set(int n, int d)
    {
        if (d > 0)
        {
            numerator = n;
            denominator = d;
            return true;
        }
        else
            return false;
    }

    public String toString()
    {
        return (numerator + "/" + denominator);
    }

    public int getNumerator()
    {
        return numerator;
    }

    public int getDenominator()
    {
        return denominator;
    }

    public double decimal()
    {
        return (double)numerator / denominator;
    }

    /**************************************************************
     *everything below this point is an edit created by Eric Adams*
     **************************************************************/
    public Fraction simplify(){
        Fraction result = new Fraction();

        if(this.numerator == 0){
            result.numerator = 0;
            result.denominator = 1;
            return result;
        }
        int tempNumerator = this.numerator;
        if(tempNumerator < 0)
            tempNumerator *= -1;
        int tempDenominator = this.denominator;
        int tempHolder;
        while(tempNumerator != 0 && tempDenominator !=0){  //finding GCD between numerator and denominator
            tempHolder = tempDenominator;
            tempDenominator = tempNumerator % tempDenominator;
            tempNumerator = tempHolder;
        }
        int gcd = tempDenominator + tempNumerator;

        result.numerator = this.numerator / gcd;
        result.denominator = this.denominator / gcd;
        return result;
    }
    public Fraction add(Fraction f){
        Fraction result = new Fraction();
        result.numerator = this.numerator * f.denominator + f.numerator * this.denominator;
        result.denominator = this.denominator * f.denominator;
        return result.simplify();
    }
    public Fraction subtract(Fraction f){
        Fraction result = new Fraction();
        result.numerator = this.numerator * f.denominator - f.numerator * this.denominator;
        result.denominator = this.denominator * f.denominator;
        return result.simplify();
    }
    public Fraction multiply(Fraction f){
        Fraction result = new Fraction();
        result.numerator = this.numerator * f.numerator;
        result.denominator = this.denominator * f.denominator;
        return result.simplify();
    }
    public Fraction divide(Fraction f){
        Fraction result = new Fraction();
        if(this.numerator == 0 || f.numerator == 0){
            result.numerator = 0;
            result.denominator = 1;
        }
        else if(f.numerator > 0 && this.numerator > 0){
            result.numerator = this.numerator * f.denominator;
            result.denominator = this.denominator * f.numerator;
        }
        else {
            if (f.numerator < 0 && this.numerator > 0) {
                result.numerator = this.numerator * f.denominator * -1;
                result.denominator = this.denominator * (f.numerator * -1);
            }
            else if(this.numerator < 0 && f.numerator > 0){
                result.numerator = this.numerator * f.denominator;
                result.denominator = this.denominator * f.numerator;
            }
            else{
                result.numerator = this.numerator * f.denominator * -1;
                result.denominator = this.denominator * (f.numerator * -1);
            }
        }
        return result.simplify();
    }

}
