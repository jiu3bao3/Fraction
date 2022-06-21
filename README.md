# Fraction
Java で分数を表現するためのクラス.

# 例
## 分数同士の足し算
```
Fraction frac1 = Fraction.parse("1/2"); // 1/2
Fraction frac2 = new Fraction(1, 3); // 1/3
System.out.printf("%s + %s = %s\n", frac1, frac2, frac1.add(frac2));
```

>  (1 / 2) + (1 / 3) = (5 / 6)

## 分数と整数との足し算

```
Fraction frac1 = Fraction.parse("1/2"); // 1/2
long value = 3L;
System.out.printf("%s + %s = %s\n", frac1, value, frac1.add(value));
```

> (1 / 2) + 3 = (7 / 2)

## 分数同士の掛け算

```
Fraction frac1 = Fraction.parse("1/2"); // 1/2
Fraction frac2 = new Fraction(1, 3); // 1/3
System.out.printf("%s × %s = %s\n", frac1, frac2, frac1.multiply(frac2));
```

> (1 / 2) × (1 / 3) = (1 / 6)

## 分数と整数の掛け算

```
Fraction frac2 = new Fraction(1, 3); // 1/3
long value = 3L;
System.out.printf("%s × %s = %s\n", frac2, value, frac2.multiply(value));
```

>  (1 / 3) × 3 = 1





