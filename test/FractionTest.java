////////////////////////////////////////////////////////////////////////////////////////////////////
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * Fractionクラスのテスト.
 * @author 久保　由仁
 */
public class FractionTest
{
    /**
     * 正しく分数文字列を解析できること
     */
    @Test
    public void parseValidStringTest()
    {
        String[] validData = { "1/2", "- 1/3" };
        for(String val : validData)
        {
            Object result = Fraction.parse(val);
            Assert.assertTrue("正しく分数文字列を解析できること", result instanceof Fraction);
        }
    }
    //----------------------------------------------------------------------------------------------
    /**
     * nullをparseするとnullとなること
     */
    @Test
    public void parseNullTest()
    {
        Object result = Fraction.parse(null);
        Assert.assertTrue("nullをparseするとnullとなること", result == null);
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 不正な文字列をparseするとIllegalArgumentExceptionがスローされること
     */
    @Test(expected=IllegalArgumentException.class)
    public void parseInvalidStringTest()
    {
        Fraction.parse("xxx");
    }
    //----------------------------------------------------------------------------------------------
    /**
     * コンストラクタでFractionオブジェクトを構築できること
     */
    @Test
    public void constructorTest()
    {
        final String message = "コンストラクタでFractionオブジェクトを構築できること";
        Object obj = new Fraction(BigInteger.ONE, new BigInteger("2"));
        Assert.assertTrue(message, obj instanceof Fraction);
        BigInteger numerator = null;
        BigInteger denominator = null;
        try
        {
            Field numeratorField = Fraction.class.getDeclaredField("numerator");
            Field denominatorField = Fraction.class.getDeclaredField("denominator");
            numerator = (BigInteger)numeratorField.get(obj);
            denominator = (BigInteger)denominatorField.get(obj);
        }
        catch(ReflectiveOperationException e)
        {
            e.printStackTrace();
        }
        Assert.assertEquals(denominator, new BigInteger("2"));
        Assert.assertEquals(numerator, new BigInteger("1"));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * コンストラクタの引数にnullを渡すとNullPointerExceptionがスローされること
     */
    @Test(expected=NullPointerException.class)
    public void constructorNullTest()
    {
        new Fraction(BigInteger.ONE, null);
    }
    //----------------------------------------------------------------------------------------------
    /**
     * コンストラクタに分母として0を与えるとIllegalArgumentExceptionがスローされること
     */
    @Test(expected=IllegalArgumentException.class)
    public void constructorWithZeroDenomialTest()
    {
        new Fraction(BigInteger.ONE, BigInteger.ZERO);
    }
    //----------------------------------------------------------------------------------------------
    /**
     * コンストラクタに整数を1つ与えると分母を1とする分数が生成されること
     */
    @Test
    public void constructWithIntegerTest()
    {
        final String message = "コンストラクタに整数を1つ与えると分母を1とする分数が生成されること";
        Object obj = new Fraction(3);
        Assert.assertTrue(message, obj instanceof Fraction);
        Assert.assertEquals(message, obj, new Fraction(3,1));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 他の分数と正しく加算できること
     */ 
    @Test
    public void addToAnotherFractionTest()
    {
        final String message = "他の分数と正しく加算できること";
        Fraction frac = new Fraction(1, 3);
        Assert.assertEquals(message, frac.add(new Fraction(1, 4)), new Fraction(7, 12));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 整数と正しく加算ができること
     */
    @Test
    public void addToIntegerTest()
    {
        final String message = "整数と正しく加算ができること";
        Fraction frac = new Fraction(1, 5);
        Assert.assertEquals(message, frac.add(2), new Fraction(11, 5));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * ０を加算しても値が変わらないこと
     */
    @Test
    public void addToZeroTest()
    {
        final String message = "０を加算しても値が変わらないこと";
        Fraction frac = new Fraction(1, 5);
        Assert.assertEquals(message, frac.add(0), frac);
    }
    //----------------------------------------------------------------------------------------------
    /**
     * nullを加算しようとするとNullPointerExceptionがスローされること
     */
    @Test(expected=NullPointerException.class)
    public void addNullTest()
    {
        Fraction frac = new Fraction(1, 5);
        BigInteger bigNull = null;
        frac.add(bigNull);
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 正しく分数を減算できること
     */
    @Test
    public void subtractAnotherFractionTest()
    {
        final String message = "正しく分数を減算できること";
        Fraction frac = new Fraction(1, 3);
        Assert.assertEquals(message, frac.subtract(new Fraction(5, 6)), new Fraction(-1,2));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 正しく整数を減算できること
     */
    @Test
    public void subtractIntegerTest()
    {
        final String message = "正しく整数を減算できること";
        Fraction frac = new Fraction(10, 3);
        Assert.assertEquals(message, frac.subtract(2), new Fraction(4,3));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 同じ値を減算した結果が0となること
     */
    @Test
    public void subtractSameValueTest()
    {
        final String message = "同じ値を減算した結果が0となること";
        Fraction frac = new Fraction(8, 3);
        Assert.assertEquals(message, frac.subtract(frac), new Fraction(0));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 他の分数と正しく乗算ができること
     */
    @Test
    public void multiplyAnotherFractionTest()
    {
        final String message = "他の分数と正しく乗算ができること";
        Fraction frac = new Fraction(2, 7);
        Assert.assertEquals(message, frac.multiply(new Fraction(1, 2)), new Fraction(1, 7));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 他の整数と正しく乗算ができること
     */
    @Test
    public void multiplyIntegerTest()
    {
        final String message = "他の整数と正しく乗算ができること";
        Fraction frac = new Fraction(2, 7);
        Assert.assertEquals(message, frac.multiply(-14), new Fraction(-4));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 0を乗じた結果が0となること
     */
    @Test
    public void multiplyZeroTest()
    {
        final String message = "他の整数と正しく乗算ができること";
        Fraction frac = new Fraction(2, 7);
        Assert.assertEquals(message, frac.multiply(0), new Fraction(0));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 他の分数で正しく除算ができること
     */
    @Test
    public void divideByAnotherFractionTest()
    {
        final String message = "他の分数で正しく除算ができること";
        Fraction frac = new Fraction(14, 27);
        Assert.assertEquals(message, frac.divide(new Fraction(2,9)), new Fraction(7, 3));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 他の整数で正しく除算ができること
     */
    @Test
    public void divideByIntegerTest()
    {
        final String message = "他の整数で正しく除算ができること";
        Fraction frac = new Fraction(14, 27);
        Assert.assertEquals(message, frac.divide(2), new Fraction(7, 27));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 0で除算しようとするとIllegalArgumentExceptionがスローされること
     */
    @Test(expected=IllegalArgumentException.class)
    public void divideByZeroTest()
    {
        Fraction frac = new Fraction(14, 27);
        frac.divide(new Fraction(0));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 値の異なる正の分数同士で大小比較を正しく行えること
     */
    @Test
    public void compareToAnotherPositiveFractionTest()
    {
        final String message = "値の異なる正の分数同士で大小比較を正しく行えること";
        Fraction frac1 = new Fraction(1, 2);
        Fraction frac2 = new Fraction(1, 3);
        Assert.assertTrue(message, frac1.compareTo(frac2) > 0);
        Assert.assertFalse(message, frac2.compareTo(frac1) > 0);
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 値の異なる負の分数同士で大小比較を正しく行えること
     */
    @Test
    public void compareToAnotherNegativeFractionTest()
    {
        final String message = "値の異なる負の分数同士で大小比較を正しく行えること";
        Fraction frac1 = new Fraction(-1, 2);
        Fraction frac2 = new Fraction(1, -3);
        Assert.assertTrue(message, frac1.compareTo(frac2) < 0);
        Assert.assertFalse(message, frac2.compareTo(frac1) < 0);
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 約分すると同じになる分数同士で大小比較を正しく行えること
     */
    @Test
    public void compareToAnotherFractionWithSameValueTest()
    {
        final String message = "約分すると同じになる分数同士で大小比較を正しく行えること";
        Fraction frac = new Fraction(4, 5);
        Assert.assertEquals(message, frac.compareTo(new Fraction(8, 10)), 0);
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 分数を文字列に変換できること
     */
    @Test
    public void toStringWithFractionTest()
    {
        final String message = "分数を文字列に変換できること";
        Fraction frac = new Fraction(3,4);
        Assert.assertEquals(message, frac.toString(), "(3 / 4)");
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 負の分数を文字列に変換できること
     */
    @Test
    public void toStringWithNegativeFractionTest()
    {
        final String message = "負の分数を文字列に変換できること";
        Fraction frac = new Fraction(-3,4);
        Assert.assertEquals(message, frac.toString(), "- (3 / 4)");
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 約分すると整数となる分数を文字列に変換できること
     */
    @Test
    public void toStringWithIntegerTest()
    {
        final String message = "約分すると整数となる分数を文字列に変換できること";
        Fraction frac = new Fraction(-8, 4);
        Assert.assertEquals(message, frac.toString(), "-2");
    }
    //----------------------------------------------------------------------------------------------
    /**
     * intValue()メソッドでint値を取得できること
     */
    @Test
    public void intValueTest()
    {
        final String message = "intValue()メソッドでint値を取得できること";
        Fraction frac = new Fraction(-8, 4);
        Assert.assertEquals(message, frac.intValue(), -2);
    }
    //----------------------------------------------------------------------------------------------
    /**
     * doubleValue()メソッドでdouble値を取得できること
     */
    @Test
    public void doubleValueTest()
    {
        final String message = "doubleValue()メソッドでdouble値を取得できること";
        Fraction frac = new Fraction(1, 4);
        Assert.assertEquals(message, frac.doubleValue(), 0.25d, 1.0e-16);
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 同じ値の分数が同値と判定されること
     */
    @Test
    public void equalsWithSameFractionTest()
    {
        final String message = "同じ値の分数が同値と判定されること";
        Fraction frac = new Fraction(3, 4);
        Assert.assertTrue(message, frac.equals(Fraction.parse("30/40")));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 同じオブジェクトが同値と判定されること
     */
    @Test
    public void equalsWithSameObjectTest()
    {
        final String message = "同じオブジェクトが同値と判定されること";
        Fraction frac = new Fraction(3, 4);
        Assert.assertTrue(message, frac.equals(frac));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * nullが同値と判定されないこと
     */
    @Test
    public void equalsWithNullTest()
    {
        final String message = "nullが同値と判定されないこと";
        Fraction frac = new Fraction(3, 4);
        Assert.assertFalse(message, frac.equals(null));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 正の分数の絶対値が算出できること
     */
    @Test
    public void absWithPositiveTest()
    {
        final String message = "正の分数の絶対値が算出できること";
        Fraction frac = new Fraction(7, 9);
        Assert.assertEquals(message, frac.abs(), frac);
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 負の分数の絶対値が算出できること
     */
    @Test
    public void absWithNegativeTest()
    {
        final String message = "正の分数の絶対値が算出できること";
        Fraction frac = new Fraction(-5, 9);
        Assert.assertEquals(message, frac.abs(), new Fraction(5, 9));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 0の分数の絶対値が算出できること
     */
    @Test
    public void absWithZeroTest()
    {
        final String message = "正の分数の絶対値が算出できること";
        Fraction frac = new Fraction(0, 9);
        Assert.assertEquals(message, frac.abs(), new Fraction(0, 5));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 小数を分数に正しく変換できること
     */
    @Test
    public void ofHalfDecimalTest()
    {
        final String message = "小数を分数に正しく変換できること";
        Fraction frac = Fraction.of(new BigDecimal("0.50"));
        Assert.assertEquals(message, frac, new Fraction(1, 2));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 負の小数を分数に正しく変換できること
     */
    @Test
    public void ofNegativeDecimalTest()
    {
        final String message = "小数を分数に正しく変換できること";
        Fraction frac = Fraction.of(new BigDecimal("-3.14"));
        Assert.assertEquals(message, frac, new Fraction(-157, 50));
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 0の小数を分数に正しく変換できること
     */
    @Test
    public void ofZeroDecimalTest()
    {
        final String message = "小数を分数に正しく変換できること";
        Fraction frac = Fraction.of(new BigDecimal("0.0000"));
        Assert.assertEquals(message, frac, new Fraction(0));
    }
}
