package cn.icodeit.cartman.core.env;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MapPropertyResolverTest {

    @Test
    public void testContains() throws Exception {
        MapPropertyResolver resolver = new MapPropertyResolver();
        resolver.map.put("key1", "value1");
        resolver.map.put("key2", "value2");
        resolver.map.put("key1", "value1-1");

        Assert.assertTrue(resolver.contains("key1"));
        Assert.assertTrue(resolver.contains("key2"));
        Assert.assertFalse(resolver.contains("key3"));
    }

    @Test
    public void testGet() throws Exception {
        MapPropertyResolver resolver = new MapPropertyResolver();
        resolver.map.put("key1", "value1");
        resolver.map.put("key2", "value2");
        resolver.map.put("key1", "value1-1");

        Assert.assertEquals("value1-1", resolver.get("key1"));
        Assert.assertEquals("value2", resolver.get("key2"));
        Assert.assertEquals(null, resolver.get("key3"));
    }

    @Test
    public void testGet1() throws Exception {
        MapPropertyResolver resolver = new MapPropertyResolver();
        resolver.map.put("key1", "value1");
        resolver.map.put("key2", "value2");
        resolver.map.put("key1", "value1-1");

        Assert.assertEquals("value1-1", resolver.get("key1", "def"));
        Assert.assertEquals("value2", resolver.get("key2", "def"));
        Assert.assertEquals("def", resolver.get("key3", "def"));
    }

    @Test
    public void testGet2() throws Exception {
        List<Object> obj = new ArrayList<>();

        MapPropertyResolver resolver = new MapPropertyResolver();
        resolver.map.put("key1", obj);
        resolver.map.put("key2", obj);

        Assert.assertEquals(obj, resolver.get("key1", ArrayList.class));
        Assert.assertEquals(obj, resolver.get("key2", ArrayList.class));
        Assert.assertEquals(null, resolver.get("key3"));
    }

    @Test
    public void testGet3() throws Exception {

    }
}