
package org.dhafirmoussa.dwrshowcase.constants;

import org.directwebremoting.annotations.DataTransferObject;


/**
 * Describes the status of an operation call.
 * 
 * @author Dhafir Moussa
 * @version 0.1
 * @date 02/06/2014
 */
// DWR will discover this Enum and will knwo to create an enum converter for it.
@DataTransferObject
public enum Status {
    SUCCESS, WARNING, ERROR, INFO;
}
