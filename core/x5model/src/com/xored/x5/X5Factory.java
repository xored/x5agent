/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.xored.x5;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.xored.x5.X5Package
 * @generated
 */
public interface X5Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	X5Factory eINSTANCE = com.xored.x5.impl.X5FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Request</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Request</em>'.
	 * @generated
	 */
	X5Request createX5Request();

	/**
	 * Returns a new object of class '<em>Response</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Response</em>'.
	 * @generated
	 */
	X5Response createX5Response();

	/**
	 * Returns a new object of class '<em>Fact</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fact</em>'.
	 * @generated
	 */
	X5Fact createX5Fact();

	/**
	 * Returns a new object of class '<em>Fact Response</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fact Response</em>'.
	 * @generated
	 */
	X5FactResponse createX5FactResponse();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	X5Package getX5Package();

} //X5Factory
