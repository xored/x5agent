/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.xored.x5agent.model.impl;

import com.xored.x5agent.model.DeliveryStatus;
import com.xored.x5agent.model.X5Fact;
import com.xored.x5agent.model.X5FactResponse;
import com.xored.x5agent.model.X5Factory;
import com.xored.x5agent.model.X5Package;
import com.xored.x5agent.model.X5Request;
import com.xored.x5agent.model.X5Response;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class X5PackageImpl extends EPackageImpl implements X5Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass x5RequestEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass x5ResponseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass x5FactEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eStringToEStringMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass x5FactResponseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum deliveryStatusEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.xored.x5agent.model.X5Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private X5PackageImpl() {
		super(eNS_URI, X5Factory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link X5Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static X5Package init() {
		if (isInited) return (X5Package)EPackage.Registry.INSTANCE.getEPackage(X5Package.eNS_URI);

		// Obtain or create and register package
		X5PackageImpl theX5Package = (X5PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof X5PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new X5PackageImpl());

		isInited = true;

		// Create package meta-data objects
		theX5Package.createPackageContents();

		// Initialize created meta-data
		theX5Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theX5Package.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(X5Package.eNS_URI, theX5Package);
		return theX5Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getX5Request() {
		return x5RequestEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getX5Response() {
		return x5ResponseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getX5Fact() {
		return x5FactEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getX5Fact_Id() {
		return (EAttribute)x5FactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getX5Fact_Clientapp() {
		return (EAttribute)x5FactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getX5Fact_Client() {
		return (EAttribute)x5FactEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getX5Fact_Timestamp() {
		return (EAttribute)x5FactEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getX5Fact_Schema() {
		return (EAttribute)x5FactEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getX5Fact_Body() {
		return (EReference)x5FactEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getX5Fact_References() {
		return (EReference)x5FactEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEStringToEStringMapEntry() {
		return eStringToEStringMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEStringToEStringMapEntry_Key() {
		return (EAttribute)eStringToEStringMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEStringToEStringMapEntry_Value() {
		return (EAttribute)eStringToEStringMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getX5FactResponse() {
		return x5FactResponseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getX5FactResponse_Status() {
		return (EAttribute)x5FactResponseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDeliveryStatus() {
		return deliveryStatusEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public X5Factory getX5Factory() {
		return (X5Factory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		x5RequestEClass = createEClass(X5_REQUEST);

		x5ResponseEClass = createEClass(X5_RESPONSE);

		x5FactEClass = createEClass(X5_FACT);
		createEAttribute(x5FactEClass, X5_FACT__ID);
		createEAttribute(x5FactEClass, X5_FACT__CLIENTAPP);
		createEAttribute(x5FactEClass, X5_FACT__CLIENT);
		createEAttribute(x5FactEClass, X5_FACT__TIMESTAMP);
		createEAttribute(x5FactEClass, X5_FACT__SCHEMA);
		createEReference(x5FactEClass, X5_FACT__BODY);
		createEReference(x5FactEClass, X5_FACT__REFERENCES);

		eStringToEStringMapEntryEClass = createEClass(ESTRING_TO_ESTRING_MAP_ENTRY);
		createEAttribute(eStringToEStringMapEntryEClass, ESTRING_TO_ESTRING_MAP_ENTRY__KEY);
		createEAttribute(eStringToEStringMapEntryEClass, ESTRING_TO_ESTRING_MAP_ENTRY__VALUE);

		x5FactResponseEClass = createEClass(X5_FACT_RESPONSE);
		createEAttribute(x5FactResponseEClass, X5_FACT_RESPONSE__STATUS);

		// Create enums
		deliveryStatusEEnum = createEEnum(DELIVERY_STATUS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		x5FactEClass.getESuperTypes().add(this.getX5Request());
		x5FactResponseEClass.getESuperTypes().add(this.getX5Response());

		// Initialize classes and features; add operations and parameters
		initEClass(x5RequestEClass, X5Request.class, "X5Request", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(x5ResponseEClass, X5Response.class, "X5Response", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(x5FactEClass, X5Fact.class, "X5Fact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getX5Fact_Id(), ecorePackage.getEString(), "id", null, 0, 1, X5Fact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getX5Fact_Clientapp(), ecorePackage.getEString(), "clientapp", null, 0, 1, X5Fact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getX5Fact_Client(), ecorePackage.getEString(), "client", null, 0, 1, X5Fact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getX5Fact_Timestamp(), ecorePackage.getELong(), "timestamp", null, 0, 1, X5Fact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getX5Fact_Schema(), ecorePackage.getEString(), "schema", null, 0, 1, X5Fact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getX5Fact_Body(), ecorePackage.getEObject(), null, "body", null, 0, 1, X5Fact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getX5Fact_References(), this.getEStringToEStringMapEntry(), null, "references", null, 0, -1, X5Fact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eStringToEStringMapEntryEClass, Map.Entry.class, "EStringToEStringMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEStringToEStringMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEStringToEStringMapEntry_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(x5FactResponseEClass, X5FactResponse.class, "X5FactResponse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getX5FactResponse_Status(), this.getDeliveryStatus(), "status", null, 0, 1, X5FactResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(deliveryStatusEEnum, DeliveryStatus.class, "DeliveryStatus");
		addEEnumLiteral(deliveryStatusEEnum, DeliveryStatus.ACCEPTED);
		addEEnumLiteral(deliveryStatusEEnum, DeliveryStatus.WONT_ACCEPT);
		addEEnumLiteral(deliveryStatusEEnum, DeliveryStatus.RETRY);

		// Create resource
		createResource(eNS_URI);
	}

} //X5PackageImpl
