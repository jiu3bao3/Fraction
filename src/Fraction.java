////////////////////////////////////////////////////////////////////////////////////////////////////
import java.io.Serializable;
import java.math.BigInteger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 分数クラス.
 * @author 久保　由仁
 */
public class Fraction extends Number implements Serializable, Comparable<Fraction>
{
    /** 分子. */
    protected BigInteger numerator = BigInteger.ZERO;
    /** 分母. */
    protected BigInteger denominator = BigInteger.ONE;
    //----------------------------------------------------------------------------------------------
    /**
     * 文字列からFractionを作成する.
     * @param val 解析対象の文字列
     * @return 作成したFraction
     * @throws IllegalArgumentException 文字列の解析に失敗した場合
     */
    public static Fraction parse(final String val)
    {
        if(val == null) return null;
        Pattern pattern = Pattern.compile("^([\\-\\+])?\\s*(\\d+)\\s*/\\s*(\\d+)$");
        Matcher matcher = pattern.matcher(val.trim());
        if(matcher.find())
        {
            BigInteger numeratorValue = new BigInteger(matcher.group(2));
            BigInteger denominatorValue = new BigInteger(matcher.group(3));
            Fraction frac = new Fraction(numeratorValue, denominatorValue);
            if(matcher.group(1) != null && matcher.group(1).equals("-"))
            {
                frac = frac.multiply(-1);
            }
            return frac;
        }
        else
        {
            throw new IllegalArgumentException(val);
        }
    }
    //----------------------------------------------------------------------------------------------
    /**
     * コンストラクタ.
     * @param numerator 分子
     * @param denominator 分母
     * @throws NullPointerException 分母または分子がnullの場合
     * @throws IllegalArgumentException 分母が0となる場合
     */
    public Fraction(final BigInteger numerator, final BigInteger denominator)
    {
        if(denominator == null || numerator == null) throw new NullPointerException();
        if(denominator.equals(BigInteger.ZERO)) throw new IllegalArgumentException();
        this.numerator = numerator;
        this.denominator = denominator;
    }
    //----------------------------------------------------------------------------------------------
    /**
     * コンストラクタ（整数を1を分母とする分数に変換する）.
     * @param numerator 整数
     */
    public Fraction(final BigInteger numerator)
    {
        this(numerator, BigInteger.ONE);
    }
    //----------------------------------------------------------------------------------------------
    /**
     * コンストラクタ.
     * @param numerator 分子
     * @param denominator 分母
     */
    public Fraction(final long numerator, final long denominator)
    {
        this(new BigInteger(Long.toString(numerator)), new BigInteger(Long.toString(denominator)));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * コンストラクタ（整数を1を分母とする分数に変換する）.
     * @param numerator 整数
     */
    public Fraction(final long numerator)
    {
        this(new BigInteger(Long.toString(numerator)));
    }
    //----------------------------------------------------------------------------------------------
    /** デフォルトコンストラクタ.
     * サブクラスから呼び出すためのコンストラクタ
     */
    protected Fraction() {}
    //----------------------------------------------------------------------------------------------
    /**
     * 加算を行う
     * @param another 加算対象の分数
     * @return 加算結果
     */
    public Fraction add(final Fraction another)
    {
        if(another == null) throw new NullPointerException();
        BigInteger gcd = denominator.gcd(another.denominator);
        BigInteger lcm = denominator.multiply(another.denominator).divide(gcd);
        BigInteger num1 = lcm.divide(denominator).multiply(numerator);
        BigInteger num2 = lcm.divide(another.denominator).multiply(another.numerator);
        Fraction result = new Fraction();
        result.denominator  = lcm;
        result.numerator = num1.add(num2);
        return result.reduce();
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 加算を行う
     * @param another 加算対象の整数
     * @return 加算結果
     */
    public Fraction add(final BigInteger another)
    {
        return add(new Fraction(another));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 加算を行う
     * @param another 加算対象の整数
     * @return 加算結果
     */
    public Fraction add(final long another)
    {
        return add(new Fraction(another));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 減算を行う.
     * @param another 減算対象の分数
     * @return 減算結果
     */
    public Fraction subtract(final Fraction another)
    {
        if(another == null) throw new NullPointerException();
        return add(another.multiply(-1));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 減算を行う.
     * @param another 減算対象の整数
     * @return 減算結果
     */
    public Fraction subtract(final BigInteger another)
    {
        return subtract(new Fraction(another));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 減算を行う.
     * @param another 減算対象の整数
     * @return 減算結果
     */
    public Fraction subtract(final long another)
    {
        return subtract(new Fraction(another));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 乗算を行う.
     * @param another 乗算対象の分数
     * @return 乗算結果
     */
    public Fraction multiply(final Fraction another)
    {
        if(another == null) throw new NullPointerException();
        Fraction result = new Fraction();
        result.numerator = numerator.multiply(another.numerator);
        result.denominator = denominator.multiply(another.denominator);
        return result.reduce();
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 乗算を行う.
     * @param another 乗算対象の整数
     * @return 乗算結果
     */
    public Fraction multiply(final BigInteger another)
    {
        if(another == null) throw new NullPointerException();
        Fraction result = new Fraction();
        result.numerator = numerator.multiply(another);
        result.denominator = denominator;
        return result.reduce();
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 乗算を行う.
     * @param another 乗算対象の整数
     * @return 乗算結果
     */
    public Fraction multiply(final long another)
    {
        return multiply(new BigInteger(Long.toString(another)));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 除算を行う.
     * @param another 除算対象の分数
     * @return 除算結果
     * @throws NullPointerException 引数にnullを与えた場合
     * @throws IllegalArgumentException 引数に0を与えた場合
     */
    public Fraction divide(final Fraction another)
    {
        if(another == null) throw new NullPointerException();
        if(another.numerator.equals(BigInteger.ZERO)) throw new IllegalArgumentException();
        Fraction result = new Fraction();
        result.numerator = numerator.multiply(another.denominator);
        result.denominator = denominator.multiply(another.numerator);
        return result.reduce();
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 除算を行う.
     * @param another 除算対象の整数
     * @return 除算結果
     * @throws NullPointerException 引数にnullを与えた場合
     * @throws IllegalArgumentException 引数に0を与えた場合
     */
    public Fraction divide(final BigInteger another)
    {
        if(another == null) throw new NullPointerException();
        if(another.equals(BigInteger.ZERO)) throw new IllegalArgumentException();
        Fraction result = new Fraction();
        result.numerator = numerator;
        result.denominator = denominator.multiply(another);
        return result.reduce();
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 除算を行う.
     * @param another 除算対象の整数
     * @return 除算結果
     * @throws IllegalArgumentException 引数に0を与えた場合
     */
    public Fraction divide(final long another)
    {
        return divide(new BigInteger(Long.toString(another)));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 値が負か否かを判定する.
     * @return true: 負, false: 正
     */
    public boolean isNegative()
    {
        BigInteger zero = BigInteger.ZERO;
        if(numerator.equals(zero))
        {
            return false;
        }
        else
        {
            return (numerator.compareTo(zero) >= 0) ^ (denominator.compareTo(zero) >= 0);
        }
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 分数の絶対値を返す.
     * @return 絶対値
     */
    public Fraction abs()
    {
        return isNegative() ? this.multiply(-1) : this;
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 約分する.
     * @return 約分した分数
     */
    protected Fraction reduce()
    {
        BigInteger gcd = numerator.gcd(denominator);
        return new Fraction(numerator.divide(gcd), denominator.divide(gcd));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 他の分数との大小を比較する.
     * @param another 比較対象の分数
     * @return 大小比較の結果
     */
    @Override
    public int compareTo(final Fraction another)
    {
        if(another == null)
        {
            throw new NullPointerException();
        }
        Fraction diff = subtract(another);
        if(diff.numerator.equals(BigInteger.ZERO))
        {
            return 0;
        }
        return diff.isNegative() ? -1 : 1;
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 分数の文字列表現を返す.
     * @return この分数を表す文字列
     */
    @Override
    public String toString()
    {
        if(denominator == null || numerator == null) return "";
        String sign = isNegative() ? "-" : "";
        Fraction reduced = this.reduce();
        BigInteger newNumerator = reduced.numerator.abs();
        BigInteger newDenominator = reduced.denominator.abs();
        if(newDenominator.equals(BigInteger.ONE))
        {
            return String.format("%s%d", sign, newNumerator);
        }
        else
        {
            return String.format("%s (%d / %d)", sign, newNumerator, newDenominator).trim();
        }
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 値が等しいか判定する.
     * @param another 比較対象オブジェクト
     * @return 等値の場合：true
     */
    @Override
    public boolean equals(final Object another)
    {
        if(another == this) return true;
        if(another == null) return false;
        if(another instanceof Fraction)
        {
            return compareTo((Fraction)another) == 0;
        }
        else
        {
            return false;
        }
    }
    //----------------------------------------------------------------------------------------------
    /**
     * このオブジェクトのhashCodeを返す.
     * @return hashCode
     */
    @Override
    public int hashCode()
    {
        Fraction reduced = reduce();
        int hash = 7;
        hash = 31 * hash + reduced.numerator.intValue();
        hash = 31 * hash + reduced.denominator.intValue();
        return hash;
    }
    //----------------------------------------------------------------------------------------------
    /**
     * double型に変換する.
     * double型で表した値（精度が落ちることに注意）
     */
    @Override
    public double doubleValue()
    {
        if(denominator == null || numerator == null)
        {
            return Double.NaN;
        }
        return numerator.doubleValue() / denominator.doubleValue();
    }
    //----------------------------------------------------------------------------------------------
    /**
     * float型に変換する.
     * float型で表した値（精度が落ちることに注意）
     */
    @Override
    public float floatValue()
    {
        return (float)doubleValue();
    }
    //----------------------------------------------------------------------------------------------
    /**
     * long型に変換する.
     * long型で表した値（精度が落ちることに注意）
     */
    @Override
    public long longValue()
    {
        return (long)doubleValue();
    }
    //----------------------------------------------------------------------------------------------
    /**
     * int型に変換する.
     * int型で表した値（精度が落ちることに注意）
     */
    @Override
    public int intValue()
    {
        return (int)doubleValue();
    }
}
