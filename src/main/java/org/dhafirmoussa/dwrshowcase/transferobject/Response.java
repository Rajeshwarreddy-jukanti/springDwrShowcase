////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2014, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////
// $Id$
// $Revision$
// $Date$
// $Author$
////////////////////////////////////////////////////////////////////////////////
package org.dhafirmoussa.dwrshowcase.transferobject;

import java.io.Serializable;

import org.dhafirmoussa.dwrshowcase.constants.Status;
import org.directwebremoting.annotations.DataTransferObject;


/**
 * This is a simple implementation of the "Envelope" pattern. Response is a Typed response object to be used as the
 * response of any operation. In addition to the operation result (data) the response also carries the status of the
 * operation and any associated messsage.
 * 
 * @author Dhafir Moussa
 * @version 0.1
 * @date 02/06/2014
 */
@DataTransferObject
public class Response<T> implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is the payload of the response.
     */
    private T data;

    /**
     * Operation status. Default is success. If operation failed then it shoudl be explicitly set to ERROR.
     */
    private Status status = Status.SUCCESS;

    /**
     * Any associated message.
     */
    private String message;

    /**
     * Default constructor
     */
    public Response() {
        // any initial setup
    }

    /**
     * Constructor to inialise the Response with payload.
     * 
     * @param data
     *            the payload data.
     */
    public Response(T data) {
        this.data = data;
    }

    /**
     * Get the data of the Response object.
     * 
     * @return Returns the data.
     */
    public T getData() {
        return data;
    }

    /**
     * Set the data member on the Response object.
     * 
     * @param data
     *            The data to set.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Get the status of the Response object.
     * 
     * @return Returns the status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the status member on the Response object.
     * 
     * @param status
     *            The status to set.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Get the message of the Response object.
     * 
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message member on the Response object.
     * 
     * @param message
     *            The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Short cut to check if the operation was successful.
     * 
     * @return
     */
    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }

}
