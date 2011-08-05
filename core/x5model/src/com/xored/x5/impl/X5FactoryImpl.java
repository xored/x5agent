/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.xored.x5.impl;

import com.xored.x5.*;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class X5FactoryImpl extends EFactoryImpl implements X5Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static X5Factory init() {
		try {
			X5Factory theX5Factory = (X5Factory)EPackage.Registry.INSTANCE.getEFactory("http://xored.com/x5/1.0"); 
			if (theX5Factory != null) {
				return theX5Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new X5FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public X5FactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case X5Package.X5_REQUEST: return createX5Request();
			case X5Package.X5_RESPONSE: return createX5Response();
			case X5Package.X5_FACT: return createX5Fact();
			case X5Package.ESTRING_TO_ESTRING_MAP_ENTRY: return (EObject)createEStringToEStringMapEntry();
			case X5Package.X5_FACT_RESPONSE: return createX5FactResponse();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case X5Package.DELIVERY_STATUS:
				return createDeliveryStatusFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case X5Package.DELIVERY_STATUS:
				return convertDeliveryStatusToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public X5Request createX5Request() {
		X5RequestImpl x5Request = new X5RequestImpl();
		return x5Request;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public X5Response createX5Response() {
		X5ResponseImpl x5Response = new X5ResponseImpl();
		return x5Response;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public X5Fact createX5Fact() {
		X5FactImpl x5Fact = new X5FactImpl();
		return x5Fact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createEStringToEStringMapEntry() {
		EStringToEStringMapEntryImpl eStringToEStringMapEntry = new EStringToEStringMapEntryImpl();
		return eStringToEStringMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public X5FactResponse createX5FactResponse() {
		X5FactResponseImpl x5FactResponse = new X5FactResponseImpl();
		return x5FactResponse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeliveryStatus createDeliveryStatusFromString(EDataType eDataType, String initialValue) {
		DeliveryStatus result = DeliveryStatus.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDeliveryStatusToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public X5Package getX5Package() {
		return (X5Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static X5Package getPackage() {
		return X5Package.eINSTANCE;
	}

} //X5FactoryImpl
