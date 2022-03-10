package com.mycomp.generator.curd;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import com.mycomp.generator.common.Counter;

public class BatchManager<T> {
    interface Adaptor<T> extends Function<Object[], T> { }
    interface BatchHandler<T> extends Consumer<List<T>> {}

    private final int batchSize;
    private final Adaptor<T> adaptor;
    private final BatchHandler<T> batchHandler;

    private BatchManager(final int batchSize, final Adaptor<T> adaptor, final BatchHandler<T> batchHandler) {
        this.batchSize = batchSize;
        this.adaptor = adaptor;
        this.batchHandler = batchHandler;
    }

    public int process(final Object[][] data) {
        final List<T> rows = new ArrayList<>(batchSize);
        final Counter counter = new Counter();

        for (int i = 1; i < data.length; i++) {
            final Object[] row = data[i];
            if (row.length > 0) {
                rows.add(adaptor.apply(row));
                counter.increment();
            }

            if (rows.size() == batchSize) {
                batchHandler.accept(rows);
                rows.clear();
            }
        }
        if (!rows.isEmpty()) {
            batchHandler.accept(rows);
        }
        return counter.getCount();
    }

}