/*
 * NCATS-COMMON
 *
 * Copyright 2019 NIH/NCATS
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package gov.nih.ncats.common.functions;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Created by katzelda on 5/30/19.
 */@FunctionalInterface
public interface ThrowableBiConsumer<K, V, E extends Throwable> {

    void accept(K k, V v) throws E;

    static <K, V, E extends Throwable> ThrowableBiConsumer<K,V,E> wrap(BiConsumer<K,V> consumer){
        Objects.requireNonNull(consumer);
        return (k,v) -> consumer.accept(k,v);
    }
}
