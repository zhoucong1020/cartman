package cn.icodeit.cartman.core;

import cn.icodeit.cartman.core.annotation.RequestMethod;
import cn.icodeit.cartman.core.route.RouteMatcher;
import cn.icodeit.cartman.core.route.RouteMatcherFactory;
import cn.icodeit.cartman.core.server.CartmanServer;
import cn.icodeit.cartman.core.server.Request;
import cn.icodeit.cartman.core.server.Response;
import cn.icodeit.cartman.core.utils.ActionFactory;

import java.lang.reflect.Method;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public abstract class CartmanBase {

    public static final int CARTMAN_DEFAULT_PORT = 8080;
    public static final boolean CARTMAN_DEFAULT_SSL = false;

    public static final boolean CARTMAN_DEFAULT_REQUIRED = true;
    public static final RequestMethod CARTMAN_DEFAULT_METHOD = RequestMethod.GET;

    public static final Transformer CARTMAN_DEFAULT_TRANSFORMER = TransformerJsonImpl.getInstance();

    public static final String CODE_TYPE="UTF-8";

    protected static boolean initialized = false;

    protected static int port = CARTMAN_DEFAULT_PORT;
    protected static boolean ssl = CARTMAN_DEFAULT_SSL;

    protected static String codeType = CODE_TYPE;

    protected static CartmanServer server;
    protected static RouteMatcher matcher;
    protected static Transformer transformer = CARTMAN_DEFAULT_TRANSFORMER;

    public synchronized static void port(int port) {
        checkInitialized();
        CartmanBase.port = port;
    }

    public static int port() {
        return CartmanBase.port;
    }

    public synchronized static void ssl(boolean ssl) {
        checkInitialized();
        CartmanBase.ssl = ssl;
    }

    public static boolean ssl() {
        return CartmanBase.ssl;
    }

    public synchronized static void converter(Transformer transformer) {
        checkInitialized();
        CartmanBase.transformer = transformer;
    }

    public synchronized static void codeType(String codeType) {
        checkInitialized();
        CartmanBase.codeType = codeType;
    }

    public static String codeType() {
        return CartmanBase.codeType;
    }

    public static Transformer converter() {
        return CartmanBase.transformer;
    }

    private static void checkInitialized() {
        if (initialized) {
            throw new IllegalStateException("All setting must before server initialized");
        }
    }

    protected static RouteImpl wrap(String path, RequestMethod requestMethod, Class<?> clazz, Method method) {
        final Action action = ActionFactory.createAction(path, requestMethod, clazz, method);
        return new RouteImpl(path) {
            @Override
            public void handle(Request request, Response response) {
                action.handle(request, response);
            }

            @Override
            public void addParam(ParamImpl param) {
                action.addParam(param);
            }
        };
    }

    protected static RouteImpl wrap(String path, Route route) {
        return new RouteImpl(path) {
            @Override
            public void handle(Request request, Response response) {
                route.handle(request, response);
            }

            @Override
            public void addParam(ParamImpl param) {
            }
        };
    }

    protected static void addRoute(RouteImpl route, RequestMethod requestMethod) {
        init();
        matcher.addRoute(route.getPath(), requestMethod, route);
    }

    public synchronized static void init() {
        if (!initialized) {
            matcher = RouteMatcherFactory.get();
            new Thread(() -> {
                server = new CartmanServer();
                server.init(port, ssl);
                server.start();
            }).start();

            initialized = true;
        }
    }
}
