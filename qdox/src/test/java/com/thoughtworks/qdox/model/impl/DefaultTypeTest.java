package com.thoughtworks.qdox.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;

import com.thoughtworks.qdox.library.ClassLibrary;
import com.thoughtworks.qdox.library.ClassLoaderLibrary;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;
import com.thoughtworks.qdox.model.JavaTypeTest;
import com.thoughtworks.qdox.model.impl.DefaultJavaSource;
import com.thoughtworks.qdox.model.impl.Type;


public class DefaultTypeTest extends JavaTypeTest<Type>
{

    public JavaSource newJavaSource( ClassLibrary library )
    {
        return new DefaultJavaSource(library);
    }

    public Type newType( String fullname )
    {
        return new Type(fullname);
    }

    public Type newType( String fullname, int dimensions )
    {
        return new Type(fullname, dimensions);
    }

    public Type newType( String fullname, int dimensions, JavaSource source )
    {
        return new Type(fullname, dimensions, source);
    }
    
    @Test
    public void testArrayType() throws Exception {
        Type type = newType("int", 1);
        assertTrue(type.isArray());
    }

    @Test
    public void testComponentType() throws Exception {
        assertNull( newType("int").getComponentType());
        assertEquals("int", newType("int", 1).getComponentType().getFullyQualifiedName());
        assertEquals("long", newType("long", 3).getComponentType().getFullyQualifiedName());
    }
    
    @Test
    public void testResolving() throws Exception {
        JavaSource src = mock(JavaSource.class);
        when(src.getImports()).thenReturn( Collections.singletonList( "foo.*" ) );
        Type type = Type.createUnresolved("Bar", 0, src);
        assertEquals(false, type.isResolved());
        
        when(src.resolveType( "Bar" )).thenReturn( "foo.Bar" );
        assertEquals(true, type.isResolved());
        assertEquals("Bar", type.getValue());
        assertEquals("foo.Bar", type.getFullyQualifiedName());
    }

}