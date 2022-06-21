# Fraction
Java で分数を表現するためのクラス.

# 例
## 分数同士の足し算(add())
```
Fraction frac1 = Fraction.parse("1/2"); // 1/2
Fraction frac2 = new Fraction(1, 3); // 1/3
System.out.printf("%s + %s = %s\n", frac1, frac2, frac1.add(frac2));
```

>  (1 / 2) + (1 / 3) = (5 / 6)

## 分数と整数との足し算(add())

```
Fraction frac1 = Fraction.parse("1/2"); // 1/2
long value = 3L;
System.out.printf("%s + %s = %s\n", frac1, value, frac1.add(value));
```

> (1 / 2) + 3 = (7 / 2)

## 分数同士の掛け算(multiply())

```
Fraction frac1 = Fraction.parse("1/2"); // 1/2
Fraction frac2 = new Fraction(1, 3); // 1/3
System.out.printf("%s × %s = %s\n", frac1, frac2, frac1.multiply(frac2));
```

> (1 / 2) × (1 / 3) = (1 / 6)

## 分数と整数の掛け算(multiply())

```
Fraction frac2 = new Fraction(1, 3); // 1/3
long value = 3L;
System.out.printf("%s × %s = %s\n", frac2, value, frac2.multiply(value));
```

>  (1 / 3) × 3 = 1


## ソート
乱数を使って10個の分数を生成し，それを昇順にソートしてみます。

```
List<Fraction> list = new ArrayList<>();
Random rand = new Random();
for(int i = 0; i < 10; i++)
{
    list.add(new Fraction(1, rand.nextInt(100)));
}

list.stream().forEach((Fraction frac) -> System.out.printf("%s\t", frac));
System.out.printf("\n");

Collections.sort(list);

list.stream().forEach((Fraction frac) -> System.out.printf("%s\t", frac));
System.out.printf("\n");
```

> (1 / 98)  (1 / 11)  (1 / 50)  (1 / 47)  (1 / 19)  (1 / 64)  (1 / 57)  (1 / 14)  (1 / 14)  (1 / 76)
> 
> (1 / 98)  (1 / 76)  (1 / 64)  (1 / 57)  (1 / 50)  (1 / 47)  (1 / 19)  (1 / 14)  (1 / 14)  (1 / 11) 


