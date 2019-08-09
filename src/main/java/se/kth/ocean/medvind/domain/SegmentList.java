package se.kth.ocean.medvind.domain;

import java.util.ArrayList;
import java.util.Collection;

public class SegmentList extends ArrayList<Segment> {
    public SegmentList(int initialCapacity) {
        super(initialCapacity);
    }

    public SegmentList() {
    }

    public SegmentList(Collection<? extends Segment> c) {
        super(c);
    }
}
