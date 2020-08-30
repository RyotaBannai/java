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

#### Array

- 基本配列数は固定。可変長を使用したいときは、`ArrayList` を使う: `配列(固定長)`と `ArrayList(可変長)`
- 配列を大きくしたい場合 : 別の配列を用意して、コピーするとよい。

```java
int[] originalArray = new int[10]
int[] littleBiggerArray = new int[15]
System.arraycopy(originalArray, 0, littleBiggerArray, 0, 10)
```
