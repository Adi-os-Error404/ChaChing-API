# ChaChing-API

ChaChing is a crypto platform that lets users search coins, view detailed price charts, post comments, manage a portfolio, and detect arbitrage opportunities. 

ChaChing-API is the backend web service that powers the core features of the app.

---

## 🚀 Prerequisites:

Ensure the following are installed on your system:

### 1. Java (JDK 17)

Download [Amazon Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)

Verify installation:

```bash
java --version
```

---

### 2. MySQL

Download MySQL Installer:
- [Mac](https://dev.mysql.com/downloads/mysql/)
- [Windows](https://dev.mysql.com/downloads/installer/)

During setup:

* Choose “Developer Default” or “Server Only”
* Set a root password you'll remember

Verify MySQL is running:

```bash
mysql --version
```
---

## 💠 MySQL Setup

```bash
mysql -u root -p
```

```sql
CREATE DATABASE ChaChing;

CREATE USER 'ChaChingUser'@'localhost' IDENTIFIED BY 'ChaChingUserPassword';
GRANT ALL PRIVILEGES ON ChaChing.* TO 'ChaChingUser'@'localhost';

FLUSH PRIVILEGES;
```
---

## 🔨 Build the App:

### Open terminal inside the project root:

#### For macOS/Linux:
```bash
./mvnw clean package
```
#### For Windows
```bash
.\mvnw clean package
```

This will generate a `.jar` file in the `target/` directory.

---

## ▶️ Run the App:

Navigate to the target folder and run:

```bash
java -jar target/ChaChing-API-0.0.1-SNAPSHOT.jar
```

The API should now be running on:

```
http://localhost:8080
```

---

## ✅ Testing the API:

Use [Postman](https://www.postman.com/) to interact with the endpoints: [Postman Shared Collection](https://adityapatel-1127555.postman.co/workspace/Aditya-Patel's-Workspace~28a5c211-870d-4fe0-818f-8302970c8042/collection/43775057-eab173bc-e18f-4d70-bbfb-9163a2d91c1d?action=share&creator=43775057)

---

## 🐛 Troubleshooting:

- ❌ **No `.jar` generated?** → Check the log file in `target/surefire-reports` for build issues.
  1. **"Access denied for user"** → Check your DB username/password in `application.properties`.
  

- **❌ `mysql` command not recognised (Windows)** → You need to update the **Environment Variables** for MySQL:
  1. **Find MySQL Installation Path**:
    
     Example:
     ```
     C:\Program Files\MySQL\MySQL Server 8.0\bin
     ```
  2. **Add to Environment Variables**:
     - **Start** → **Search "Advanced System Settings"**
     - **Advanced system settings** → **Environment Variables**.
     - Under **System Variables**, select **Path** → **Edit** → **New** and paste MySql path.
     - Click **OK** to save.
  3. **Verify**:
     - Open a new **Command Prompt** and run:
       ```bash
       mysql --version
       ```
---



