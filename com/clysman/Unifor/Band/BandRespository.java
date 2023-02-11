package com.clysman.Unifor.Band;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BandRespository {
    private final List<Band> bands = new ArrayList<>();

    private List<Band> partialBands = new ArrayList<>();

    public BandRespository() {
    }

    public void add(Band band) {
        this.bands.add(band);
    }

    public BandRespository where(Predicate<? super Band> condition) {
        this.partialBands = this.bands.stream()
                .filter(condition)
                .collect(Collectors.toList());

        return this;
    }

    public BandRespository sort(Comparator<Band> comparator, String order) {
        if (order.equalsIgnoreCase("desc")) {
            comparator = comparator.reversed();
        }

        if (this.partialBands.isEmpty()) {
            this.partialBands = this.bands;
        }

        this.bands.sort(comparator);
        this.partialBands.sort(comparator);

        return this;
    }

    public int count() {
        return this.partialBands.size();
    }

    public List<Band> get() {
        return this.partialBands;
    }

    public Band first() {
        return this.partialBands.get(0);
    }
}
