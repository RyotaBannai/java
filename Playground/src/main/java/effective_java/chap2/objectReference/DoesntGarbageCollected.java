package effective_java.chap2.objectReference;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.stream.Stream;

/**
 * null を設定することに過敏になる必要はないが、
 * ・クラスが独自のメモリを管理している時にはメモリリークに対して注意を払う必要がある
 */

public class DoesntGarbageCollected {
    public static void main(String[] args) {
        Stack myStack = new Stack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        Stream.of(myStack.get()).forEach(System.out::println); // 1,2,3,null,null
    }

    private static class Stack {
        private static Object[] elements;
        private static int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 2;

        public Object[] get() {
            return elements;
        }

        public Stack() {
            this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
        }

        public void push(Object element) {
            ensureCapacity();
            elements[size++] = element;
        }

        public Object pop() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            Object result = elements[--size];

            // size　を小さくした時に、オブジェクトの参照が保持されたままである = Obsolete Reference
            // 使われなくなった要素には必ず　null を追加して参照を取り除く.
            elements[size] = null;
            return result;
        }

        /**
         * 配列のサイズを倍に大きくする
         */
        private void ensureCapacity() {
            if (elements.length == size) {
                // Copies the specified array, truncating or padding with nulls in this case
                elements = Arrays.copyOf(elements, 2 * size + 1);
            }
        }
    }

    private static void p(Object... inputs) {
        Arrays.stream(inputs).forEach(System.out::println);
    }
}
