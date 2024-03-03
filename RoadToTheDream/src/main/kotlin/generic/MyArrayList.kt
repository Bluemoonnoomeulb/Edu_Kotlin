package generic

import java.lang.IndexOutOfBoundsException

class MyArrayList<T> : MyList<T> {
    private var array: Array<Any?> = arrayOfNulls(10)
    private var size = 0

    override fun get(index: Int): T {
        if (index in 0 until size) {
            array[index]?.let {
                return it as T
            }
        }
        throw IndexOutOfBoundsException("Выход за границы массива")
    }

    override fun add(element: T) {
        if (size >= array.size) {
            array = array.copyOf(array.size * 2)
        }
        array[size] = element
        size++
    }

    override fun remove(element: T) {
        for ((index, string) in array.withIndex()) {
            if (string == element) {
                removeAt(index)
                return
            }
        }
    }

    override fun removeAt(index: Int) {
        if (index in 0 until size) {
            for (i in index until size - 1) {
                array[i] = array[i + 1]
            }
            size--
        } else {
            throw IndexOutOfBoundsException("Выход за границы массива")
        }
    }

    override fun size(): Int = size

    companion object {

        fun <T> myListOf(vararg elements: T): MyArrayList<T> {
            val list = MyArrayList<T>()

            elements.forEach {
                list.add(it)
            }
            return list
        }
    }
}