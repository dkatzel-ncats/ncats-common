/*
 *     NCATS-COMMON
 *
 *     Written in 2019 by NIH/NCATS
 *
 *     To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 *     You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package gov.nih.ncats.common.functions;

import java.util.function.BiConsumer;

/**
 * Created by katzelda on 5/30/19.
 */
public interface ThrowableBiConsumer<K, V, E extends Throwable> {

    void accept(K k, V v) throws E;
}
