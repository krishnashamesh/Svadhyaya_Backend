package com.svadhyaya.backend.populator.template;

public interface Populator<SOURCE, TARGET> {
    public void populate(SOURCE source, TARGET target);
}
