/**
 * Author: Amir H. Ebrahimnezhad
 * Contact: me@thisismeamir.com
 * All rights of the code is reserved.
 */
package core.utils.computational

inline fun <reified T> checkType(variable: Any): Boolean {
    return variable is T
}
