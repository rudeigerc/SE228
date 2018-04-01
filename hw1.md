# hw1
## Requirement
- To build an RMI service in E-Book Store for shipping
  - Suppose an Express Company, such as UPS and DHL, has developed a Java RMI service for placing orders.
  - Develop a such RMI service to receive the information of order, and return a default result to client, such as “Processing”
  - Run this service to simulate the Express Company.
  - Develop an RMI client in E-Book Store to invoke the running RMI service.

## Files
- `src/main/java/bookstore/rmi`
- `src/main/java/bookstore/action/order/GetStatusAction.java`
- `web/js/order.js`

## Usage
1. Build `src/main/java/bookstore/rmi/ExpressServer.java` and run the server.
2. Run Tomcat.
3. Login and visit the orders at the admin page, and then select a row and press the `Status` button, an alert window will appear with message.

## Implementation
### Express.Java
```java
package bookstore.rmi;

import java.rmi.*;

public interface Express extends Remote {
    String getStatus(String orderId) throws RemoteException;
}
```

### ExpressImpl.java
```java
package bookstore.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ExpressImpl extends UnicastRemoteObject implements Express {

    ExpressImpl() throws RemoteException {
        super();
    }

    public String getStatus(String orderId) throws RemoteException {
        // return the message with orderId
        return String.format("Order #%s: Processing", orderId);
    }
}
```

### ExpressServer.java
```java
package bookstore.rmi;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ExpressServer {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        System.out.println("Constructing server implementation...");
        ExpressImpl express = new ExpressImpl();
        LocateRegistry.createRegistry(1199);
        java.rmi.Naming.rebind("rmi://localhost:1199/express", express);
        System.out.println("Waiting for invocations from clients...");
    }
}
```

### ExpressClient.java
```java
package bookstore.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ExpressClient {

    public String getStatus(String orderId) throws RemoteException, NotBoundException, MalformedURLException {
        Express express = (Express)Naming.lookup("rmi://localhost:1199/express");
        return express.getStatus(orderId);
    }
}
```

### GetStatusAction.java
```java
package bookstore.action.order;

import bookstore.action.BaseAction;
import bookstore.rmi.ExpressClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class GetStatusAction extends BaseAction {

    private int orderId;
    private String json;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String execute() throws Exception {
        ExpressClient client = new ExpressClient();
        Map<String, Object> result = new HashMap<String, Object>();
        String status = client.getStatus(Integer.toString(orderId));
        result.put("status", status);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        json = gson.toJson(result);
        return SUCCESS;
    }
}
```

### struts.xml
```xml
<action name="status" class="bookstore.action.order.GetStatusAction">
    <result name="success" type="json">
        <param name="root">json</param>
        <param name="includeProperties">status</param>
    </result>
</action>
```

### order.js
```javascript
$('#status').click( function () {
    var row = get_selected_row();
    $.ajax({
        url: "status",
        data: { "orderId": row.orderId },
        type: "post",
        success: function (data) {
            const status = JSON.parse(data).status;
            window.alert(status);
        }
    });
});
```
