package com.windthunderstudio.logic.util;

import java.awt.Font;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.portable.ValueBase;

public class ReflectionUIHandler {
    private static final Logger log = LogManager.getLogger(ReflectionUIHandler.class);
    
    /**
     * Load a List of object of specified class defined as fields of an instance, using reflection <br/>
     * To use this method, the objects to be loaded should be fields of the instance,
     *  and their getters must be defined. <br/>
     * @param className the full qualified name of the class to load
     * @param ownerInstance the instance who is the owner of all the fields
     * @return list of objects (of subtype of JComponent)
     */
    public static List<JComponent> loadComponentsByClass(String className, Object ownerInstance) {
        List<JComponent> listComponents = new ArrayList<JComponent>();
        
        // get all declared fields
        Field fields[] = ownerInstance.getClass().getDeclaredFields();

        for (Field field : fields) {
            // search in the field the given className
            if (field.getType().getName().equalsIgnoreCase(className)) {
                try {
                    // obtain the getter method
                    Method getter = ownerInstance.getClass().getDeclaredMethod(
                            "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1),
                            (Class[]) null);

                    // change access modifier
                    getter.setAccessible(true);

                    // call this method from the class where the field is defined
                    Object objeto = getter.invoke(ownerInstance, (Object[]) null);

                    // if getter gets result, save the object
                    if (objeto != null) {
                        listComponents.add((JComponent) objeto);
                    }
                } catch (Exception e) {
                    log.debug("Reflection error: ", e);
                }
            }
        }
        return listComponents;
    }
    /**
     * Set the property on certain class on each JComponent in the list.
     * @param components all the JComponent needs to be configured.
     * @param propertyName the property name to be set. Will be used to find the corresponding "setter" method.
     * @param value the value to be set.
     */
    public static void setPropertyByClass(List<JComponent> components, String propertyName, Object value) {
        for (JComponent c: components) {
            //ensure the first letter is captalized
            String newProp = CommonUtils.capitalizeString(propertyName);
            //get the setter method, and call it.
            try {
                Method setter = c.getClass().getMethod("set" + newProp, c.getClass());
                setter.invoke(c, value);
            } catch (NoSuchMethodException | SecurityException | 
                    IllegalAccessException | IllegalArgumentException | 
                    InvocationTargetException e) {
                log.debug("Error when setting property to " + propertyName + ": ", e);
                throw new RuntimeException(e);
            }
            
        }
    }
    public static void getAndSetProperty(Object component, String propertyName, String originProperty) {
        
        String newProp = CommonUtils.capitalizeString(propertyName);
        String newOrigin = CommonUtils.capitalizeString(originProperty);
        try {
            //get the getter method, retrieve the value of getter return type as value to set
            Method getter = component.getClass().getMethod("get" + newOrigin, (Class[])null);
            getter.setAccessible(true);
            Object value = getter.invoke(component, (Object[])null);
            //get the setter method, and call it.
            Method setter = component.getClass().getMethod("set" + newProp, component.getClass());
            setter.setAccessible(true);
            setter.invoke(component, value);
        } catch (NoSuchMethodException | SecurityException | 
                IllegalAccessException | IllegalArgumentException | 
                InvocationTargetException e) {
            log.debug("Error when getting property from " + originProperty + " and setting property to " + propertyName + ": ", e);
            throw new RuntimeException(e);
        }
    }
    
    
}
