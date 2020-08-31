### Memo

- 数値リテラルにサフィックスを付けて型を指定する

```java
System.out.println(2200000000L) // long
// 3.2    # double型
// 3.2D   # double型
// 3.2F   # float型
```

- `var` を使用することで変数の宣言時にデータ型の記述を省略することができる。データ型を省略する代わりに初期化で変数に代入した値から自動的にデータ型を推測して設定（型推論）。

- `a instanceof b`: 参照型変数 a が b `クラス/ インターフェース/ 配列 のインスタンス`かどうか
- [型変換の基本ルール](https://www.javadrive.jp/start/cast/index1.html)
  - `Narrowing Casting` - converting a smaller type to a larger type size: 手動 `double myDouble = 9.78; int myInt = (int) myDouble; // Outputs 9`
    - `byte -> short -> char -> int -> long -> float -> double`
  - `Widening Casting` - converting a larger type to a smaller size type: 自動 `int myInt = 9; double myDouble = myInt;`
    - `double -> float -> long -> int -> char -> short -> byte`
- long 型の値を float 型 や double 型 に変換した場合、有効桁数を超えた部分については元の値と異なることに注意。[ref](https://www.javadrive.jp/start/cast/index2.html)

```text
char   0～65535
short  -32768～32767
char   0～65535
byte   -128～128
```

#### Array

- 基本配列数は固定。可変長を使用したいときは、`ArrayList` を使う: `配列(固定長)`と `ArrayList(可変長)`
- 配列を大きくしたい場合: 別の配列を用意して、コピーするとよい。

```java
int[] originalArray = new int[10]
int[] littleBiggerArray = new int[15]
System.arraycopy(originalArray, 0, littleBiggerArray, 0, 10)
```

- 多次元配列の初期化: 次のうちどれかを使う.

```java
int[] multiArray[];
int multiArray[][];
int[][] multiArray;
```

- `int num[][] = {{1, 3, 5}, {2, 4, 6}};`: 一度に代入も可能

### String

- 文字列の比較には `==` を使わない。代わりに `equals` メソッドを使う
- 文字列の長さは length `メソッド`

### Method

- メソッドの`オーバーロード`: Java では引数のデータ型や引数の数が完全に一致していなければ異なるメソッドに同じメソッド名を付けることが出来る
  - オーバーロードができない場合:
  1. 引数の変数名だけが異なっている場合
  2. 戻り値だけが異なっている場合

```java
public static void main(String args[]){
  int n = plus(10, 7);
  System.out.println(n);

  double d = plus(3.14, 7.4);
  System.out.println(d);
}

private static int plus(int n1, int n2){
  return n1 + n2;
}

private static double plus(double d1, double d2){
  return d1 + d2;
}
```

- `可変引数`

```java
private static void disp(int... num){
  for (int i = 0; i < num.length; i++){
    System.out.println(num[i]);
  }
}
```

### Regex

- `^` が行の先頭にマッチしたのに対して `\A` は`文字列の`先頭にマッチ `String reg = "\\Apeople";`
- `文字列の`末尾とマッチさせたいなら `\Z` `String reg = "people\\Z";`
  - 文の末尾に`行末文字`があった場合には行末文字を`除いた`後の行末にマッチ(どのような文字が行末文字として扱われるのか確認)
- `単語境界/ 非単語境界( ¥b , ¥B )`
  - `¥b`が単語の境界にマッチ
  - `¥B`は単語の境界以外にマッチ
