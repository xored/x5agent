/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.xored.x5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Delivery Status</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.xored.x5.X5Package#getDeliveryStatus()
 * @model
 * @generated
 */
public enum DeliveryStatus implements Enumerator {
	/**
	 * The '<em><b>Accepted</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ACCEPTED_VALUE
	 * @generated
	 * @ordered
	 */
	ACCEPTED(0, "Accepted", "Accepted"),

	/**
	 * The '<em><b>Wont Accept</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WONT_ACCEPT_VALUE
	 * @generated
	 * @ordered
	 */
	WONT_ACCEPT(1, "WontAccept", "WontAccept"),

	/**
	 * The '<em><b>Retry</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RETRY_VALUE
	 * @generated
	 * @ordered
	 */
	RETRY(2, "Retry", "Retry");

	/**
	 * The '<em><b>Accepted</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Accepted</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ACCEPTED
	 * @model name="Accepted"
	 * @generated
	 * @ordered
	 */
	public static final int ACCEPTED_VALUE = 0;

	/**
	 * The '<em><b>Wont Accept</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Wont Accept</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WONT_ACCEPT
	 * @model name="WontAccept"
	 * @generated
	 * @ordered
	 */
	public static final int WONT_ACCEPT_VALUE = 1;

	/**
	 * The '<em><b>Retry</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Retry</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RETRY
	 * @model name="Retry"
	 * @generated
	 * @ordered
	 */
	public static final int RETRY_VALUE = 2;

	/**
	 * An array of all the '<em><b>Delivery Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DeliveryStatus[] VALUES_ARRAY =
		new DeliveryStatus[] {
			ACCEPTED,
			WONT_ACCEPT,
			RETRY,
		};

	/**
	 * A public read-only list of all the '<em><b>Delivery Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<DeliveryStatus> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Delivery Status</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DeliveryStatus get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DeliveryStatus result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Delivery Status</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DeliveryStatus getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DeliveryStatus result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Delivery Status</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DeliveryStatus get(int value) {
		switch (value) {
			case ACCEPTED_VALUE: return ACCEPTED;
			case WONT_ACCEPT_VALUE: return WONT_ACCEPT;
			case RETRY_VALUE: return RETRY;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DeliveryStatus(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //DeliveryStatus
