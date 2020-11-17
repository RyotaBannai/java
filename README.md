### Memo

#### Objects#requireNonNull

- null check を簡潔に行う. prop が null の場合 NPE を返す。[ref](https://saiya-moebius.hatenablog.com/entry/2014/08/17/175510)

```java
public Hoge(String huga){
  this.huga = Objects.requireNonNull(huga);
  }
```

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

- Java Regex Syntax list [ref](http://tutorials.jenkov.com/java-regex/syntax.html)
- `^` が行の先頭にマッチしたのに対して `\A` は`文字列の`先頭にマッチ `String reg = "\\Apeople";`
- `文字列の`末尾とマッチさせたいなら `\Z` `String reg = "people\\Z";`
  - 文の末尾に`行末文字`があった場合には行末文字を`除いた`後の行末にマッチ(どのような文字が行末文字として扱われるのか確認)
- `単語境界/ 非単語境界( ¥b , ¥B )`

  - `¥b`が単語の境界にマッチ
  - `¥B`は単語の境界以外にマッチ

- `最短一致数量子 +?`: 最も短い部分にマッチ `"ab+?"` `[ab]c [ab]bc [ab]bbc`: ab にマッチ（最短にマッチ）したらそれ以降は無視
- `絶対最大指定子 ++`: パターン全体がマッチするかどうかは考慮せず、とにかく一番長い部分にマッチ　`abcdefg`　`.++g` `.++`部分が全てにマッチするため、g にマッチする部分が残されていないため、結果的にマッチしない。
- `?` 0 か 1 回以上の繰り返して、あれば 1 回としてカウント（`最長一致数量子`）
- `??` 0 か 1 回以上の繰り返して、あっても 1 回としてカウントしない（`最短一致数量子`）

- パターンの中でグループ化のために括弧を使ってはいるが、`インデックス割り当ては行わずマッチする部分文字列の参照もしないという場合`には`()`の代わりに`(?:)`を使う
- `後方参照` ではバックスラッシュとインデックスを組み合わせる `\1`
- [正規表現を使った置換](https://www.javadrive.jp/start/regex/replace/)

- `共変戻り値(型)`（covariant return type）:メソッドをオーバーライドするときに、オーバーライドするメソッドの戻り値の型が、オーバーライドされたメソッドの戻り値の型のサブタイプになることを許可すること.（共変の反対は `反変（contravariant）`と言うが、Java とは無関係）
  - インターフェースとクラス間だけでなく、複数のインターフェース間とクラスでも可
  - [reference1](https://blogs.oracle.com/sundararajan/covariant-return-types-in-java)
  - [reference2](https://www.ne.jp/asahi/hishidama/home/tech/java/covariant.html)

```java
// class は Object のサブクラス
public class MyFoo{
   // Note covariant return here, method does not just return Object
   public MyFoo clone(){
       // Implementation
   }
}
// another example
public class Sample{ // 正しく動作させるには Cloneable を implements する
  private String value;

  public Sample(String value){
    this.value = value;
  }

  public String toString(){
    return value;
  }

  public Object clone() throws CloneNotSupportedException{
    // error Object#clone() の中でjava.lang.CloneNotSupportedException が発生。
    // Object#clone()を呼び出している Sample クラスが Cloneable を実装していないため！
    return super.clone();
  }
}
Sample s1 = new Sample("abc");
Sample s2 = (Sample)s1.clone(); // error
System.out.println(s2);

// 共変戻り値を使うと...

public class CloneSample implements Cloneable{
  @Override
  public CloneSample clone() throws CloneNotSupportedException { // 戻り値にサブクラスを指定.
    return (CloneSample) super.clone();
  }
}

CloneSample obj = new CloneSample();
CloneSample clone = obj.clone(); // キャスト不要
// 参考 https://www.ne.jp/asahi/hishidama/home/tech/java/clone.html#h2_covariant
```

- `clone`

  - 複製が生成される際、新しいオブジェクトが作られるけれども、そのコンストラクターが呼ばれることは無い。
  - コピー方法は、シャローコピー（shallow copy：浅いコピー）
  - `shallow copy`: オブジェクトのフィールド（メンバー変数）がオブジェクト（参照型）である場合に、その参照をコピーするだけ。つまりフィールドのオブジェクトは、複製元と複製先で同じオブジェクトを指すことになる。
  - `deep copy`:「フィールドのオブジェクト自身も複写する方式」を指す。
    - ディープコピーを自動的に行うメソッドは用意されていないので、オブジェクトのディープコピーを作りたいのであれば、ディープコピー用のメソッドを自分で作る必要がある。

- `合成メソッド`:`共変戻り値`を使ったときなど、`コンパイラが自動的にメソッドを作成することがある`。この自動的に作成したメソッドを合成メソッドという。
  - たとえば、共変戻り値を使ったときは、スーパークラスと同じ戻り値の型のメソッド(今までのオーバーライドのイメージ)がコンパイラによって作られる(戻り値の型を変えて実装したメソッドとは別に)。これが合成メソッド。
  - さらに、共変戻り値での合成メソッドは、`内部的には新たに戻り値の型を変えたメソッドに処理を委譲する`、という実装になっている。
  - `配列は共変なので、配列は共変戻り値に出来る`（ただし、たぶん推奨されない）。`総称型（List等）は共変ではない`ので、共変戻り値に出来ない
  - 共変戻り値が指定できるのはあくまでサブクラス（派生クラス）なので、`プリミティブ型は対象外`。

### (Auto)Boxing and Unboxing

- `Boxing`: プリミティブ型（基本型）から参照(クラス型・ラッパークラス)型へ自動キャストする仕組み int -> Integer
- `Unboxing`: ら参照(ラッパークラス)型からプリミティブ型へ自動キャストする仕組み Integer -> int
- [ref](https://qiita.com/chihiro/items/870eca6e911fa5cd8e58)

#### なぜ 参照型を使うか

- Generics にはプリミティブ型は使えないため. (List, HashMap 等々) (これは、プリミティブ方が Object ではないため.)
  - OptionalInt や IntStream とか一部直接扱える物があるが、int、long、double しかなくない.
  - `List<Integer>`とかに入れていくときオートボクシングが発生しないため、速い。(その代わり、普通の四則演算とか、代入ではオートボクシングしまくりで遅い)
- [ref](https://teratail.com/questions/59136)
