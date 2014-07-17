
package org.dhafirmoussa.dwrshowcase.constants;

import org.directwebremoting.annotations.DataTransferObject;


/**
 * Department types.
 * 
 * @author Dhafir Moussa
 * @version 0.1
 * @date 22/10/2012
 */
// DWR will discover this Enum and will knwo to create an enum converter for it.
@DataTransferObject(type = "enum")
public enum Department {
    TECHNICAL, SERVICE, FINANCE, RESOURCE, PARTS;
}
