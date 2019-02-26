package com.github.isturdy.aiflagtool

import java.util.concurrent.atomic.AtomicInteger

class RingBuffer<T>(private val maxSize: Int) : Iterable<T>, Cloneable {
    private var head: Int = 0
    var size: Int = 0
    private val elements = arrayOfNulls<Any?>(maxSize)

    fun add(item: T) {
        elements[head] = item
        head = (head + 1) % maxSize
        if (size < maxSize) ++size
    }

    @Suppress("UNCHECKED_CAST")
    fun peek(): T {
        return elements[(head - 1 + maxSize) % maxSize] as T
    }

    // Indexed from the head
    @Suppress("UNCHECKED_CAST")
    operator fun get(index: Int): T {
        if (size == 0 || index > size || index < 0) throw IndexOutOfBoundsException("$index")
        return elements[(head - index - 1 + maxSize) % maxSize] as T
    }

    // Iterates over elements starting with the oldest.
    override fun iterator(): Iterator<T> = object : Iterator<T> {
        private val index: AtomicInteger = AtomicInteger(size - 1)

        override fun hasNext(): Boolean = index.get() >= 0

        override fun next(): T = get(index.getAndDecrement())
    }
}