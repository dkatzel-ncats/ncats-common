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

import java.util.Objects;
@FunctionalInterface
public interface BiIndexedConsumer<T> {
    /**
     * Performs this operation on the given arguments.
     *
     * @param x the first index argument
     * @param y the second index argument
     * @param t the object
     */
    void accept(int x, int y, T t);

    /**
     * Returns a composed {@code BiIndexedConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code BiIndexedConsumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default BiIndexedConsumer<T> andThen(BiIndexedConsumer<T> after) {
        Objects.requireNonNull(after);

        return (x, y, t) -> {
            accept(x, y, t);
            after.accept(x, y, t);
        };

    }
}

