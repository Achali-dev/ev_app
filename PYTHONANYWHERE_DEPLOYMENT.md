# 🐍 PythonAnywhere Deployment Guide - Smart EV App

## 🎯 What We'll Do

Deploy your Smart EV application on PythonAnywhere - a **100% FREE** Python hosting platform that's perfect for Django apps!

**Deployment Time:** ~20-30 minutes  
**Cost:** FREE forever  
**Difficulty:** Easy (step-by-step)

---

## 📋 Prerequisites

- ✅ GitHub account with your code pushed (already done!)
- ✅ Email address for PythonAnywhere signup
- ✅ No credit card needed!

---

## 🚀 Step 1: Sign Up for PythonAnywhere

### 1.1 Create Account

1. Visit: **https://www.pythonanywhere.com/registration/register/beginner/**
2. Fill in the form:
   - **Username:** Choose carefully (this will be in your URL: `username.pythonanywhere.com`)
   - **Email:** Your email address
   - **Password:** Create a strong password
3. Click **"Register"**
4. Check your email and verify your account

### 1.2 Log In

1. Go to: **https://www.pythonanywhere.com/login/**
2. Enter your username and password
3. You'll see the **Dashboard**

---

## 🔧 Step 2: Clone Your GitHub Repository

### 2.1 Open a Bash Console

1. In PythonAnywhere Dashboard, click **"Consoles"** tab
2. Click **"Bash"** under "Start a new console"
3. A terminal will open

### 2.2 Clone Your Repository

In the Bash console, run these commands:

```bash
# Navigate to home directory
cd ~

# Clone your repository
git clone https://github.com/Achali-dev/ev_app.git

# Navigate into the project
cd ev_app

# List files to verify
ls -la
```

You should see your project files: `manage.py`, `Smart_EV/`, `Smart_EV_App/`, etc.

---

## 🎨 Step 3: Create Virtual Environment

### 3.1 Create virtualenv

```bash
# Create a virtual environment with Python 3.10
mkvirtualenv --python=/usr/bin/python3.10 smartev_env

# The environment will activate automatically (you'll see (smartev_env) in the prompt)
```

### 3.2 Install Dependencies

```bash
# Make sure you're in the project directory
cd ~/ev_app

# Install all required packages
pip install -r requirements.txt
```

**Note:** Installation may take 2-3 minutes. Wait for it to complete.

---

## 🗄️ Step 4: Set Up MySQL Database

PythonAnywhere's free tier uses **MySQL** instead of PostgreSQL.

### 4.1 Create Database

1. Go to **Dashboard** → **"Databases"** tab
2. Under **"Create a database"**, you'll see:
   - Database name: `yourusername$smartevdb`
3. Set a **MySQL password** (write it down!)
4. Click **"Initialize MySQL"**
5. Scroll down and click **"Create"** under "Create database"
   - Database name: Enter `smartevdb`
   - Click **"Create"**

Your database is now created as: `yourusername$smartevdb`

### 4.2 Note Your Database Credentials

Keep these handy:
- **Database name:** `yourusername$smartevdb`
- **Username:** `yourusername`
- **Password:** (the password you just set)
- **Host:** `yourusername.mysql.pythonanywhere-services.com`

---

## ⚙️ Step 5: Configure Django Settings

### 5.1 Update settings.py for PythonAnywhere

Back in the Bash console:

```bash
# Edit settings.py
nano ~/ev_app/Smart_EV/settings.py
```

### 5.2 Find the DATABASES section and update it:

Press `Ctrl+W` to search for "DATABASES", then update the section to look like this:

```python
# Database configuration
DATABASE_URL = os.environ.get('DATABASE_URL')

if DATABASE_URL:
    # Production - use database from environment
    DATABASES = {
        'default': dj_database_url.config(
            default=DATABASE_URL,
            conn_max_age=600,
            conn_health_checks=True,
        )
    }
else:
    # PythonAnywhere or Development
    DATABASES = {
        'default': {
            'ENGINE': 'django.db.backends.mysql',
            'NAME': 'yourusername$smartevdb',  # Replace 'yourusername' with YOUR PythonAnywhere username
            'USER': 'yourusername',             # Replace with YOUR username
            'PASSWORD': 'your_mysql_password',  # Replace with your MySQL password
            'HOST': 'yourusername.mysql.pythonanywhere-services.com',  # Replace 'yourusername'
            'OPTIONS': {
                'init_command': "SET sql_mode='STRICT_TRANS_TABLES'",
            },
        }
    }
```

**IMPORTANT:** Replace:
- `yourusername` → Your actual PythonAnywhere username (3 places)
- `your_mysql_password` → Your MySQL password

### 5.3 Update ALLOWED_HOSTS

Find `ALLOWED_HOSTS` and update it:

```python
ALLOWED_HOSTS = ['yourusername.pythonanywhere.com', 'localhost', '127.0.0.1']
```

Replace `yourusername` with your actual username.

### 5.4 Save and Exit

- Press `Ctrl+X`
- Press `Y` to confirm
- Press `Enter` to save

---

## 📦 Step 6: Run Migrations and Collect Static Files

### 6.1 Activate Virtual Environment (if not activated)

```bash
workon smartev_env
```

### 6.2 Navigate to Project

```bash
cd ~/ev_app
```

### 6.3 Run Django Commands

```bash
# Run migrations
python manage.py migrate

# Create static files directory
mkdir -p ~/ev_app/staticfiles

# Collect static files
python manage.py collectstatic --noinput

# Create superuser (admin account)
python manage.py createsuperuser
```

For the superuser:
- **Username:** admin (or your choice)
- **Email:** your email
- **Password:** Create a secure password (you'll need this to access /admin/)

---

## 🌐 Step 7: Create Web App

### 7.1 Go to Web Tab

1. Click **"Web"** tab in Dashboard
2. Click **"Add a new web app"**
3. Click **"Next"** (accept domain name)
4. Select **"Manual configuration"**
5. Select **"Python 3.10"**
6. Click **"Next"**

### 7.2 Configure Web App

You'll see the web app configuration page.

#### **Set Virtual Environment Path:**

1. Scroll to **"Virtualenv"** section
2. In the input field, enter:
   ```
   /home/yourusername/.virtualenvs/smartev_env
   ```
   (Replace `yourusername` with your actual username)
3. **Important:** Click the ✓ checkmark or press Enter to save!

#### **Edit WSGI Configuration File:**

1. Scroll to **"Code"** section
2. Click on the **WSGI configuration file** link (e.g., `/var/www/yourusername_pythonanywhere_com_wsgi.py`)
3. This opens a text editor

#### **Replace ALL content** with this:

```python
import os
import sys

# Add your project directory to the sys.path
path = '/home/yourusername/ev_app'  # Replace 'yourusername' with your username
if path not in sys.path:
    sys.path.insert(0, path)

# Set environment variable for Django settings
os.environ['DJANGO_SETTINGS_MODULE'] = 'Smart_EV.settings'

# Import Django's WSGI handler
from django.core.wsgi import get_wsgi_application
application = get_wsgi_application()
```

**Replace `yourusername`** with your actual PythonAnywhere username in the path.

4. Click **"Save"** at the top right

---

## 🎨 Step 8: Configure Static Files

Still on the **Web** tab:

### 8.1 Static Files Mapping

Scroll to **"Static files"** section:

1. **First mapping:**
   - **URL:** `/static/`
   - **Directory:** `/home/yourusername/ev_app/staticfiles`
   
2. **Second mapping (click "Enter path"):**
   - **URL:** `/media/`
   - **Directory:** `/home/yourusername/ev_app/media`

(Replace `yourusername` with your username)

3. Click the ✓ checkmark after each entry

---

## 🚀 Step 9: Launch Your App!

### 9.1 Reload the Web App

1. At the top of the **Web** tab, you'll see a big **green "Reload" button**
2. Click **"Reload yourusername.pythonanywhere.com"**
3. Wait for it to reload (~10 seconds)

### 9.2 Visit Your Website!

Click on the link: **https://yourusername.pythonanywhere.com**

You should see:
```json
{"status": "OK", "message": "Smart EV Server is running correctly."}
```

🎉 **SUCCESS!** Your API is now live!

---

## ✅ Step 10: Test Your API

### 10.1 Test Endpoints

Your API is now accessible at: `https://yourusername.pythonanywhere.com`

**Available Endpoints:**

1. **Root:**
   ```
   https://yourusername.pythonanywhere.com/
   ```

2. **Admin Panel:**
   ```
   https://yourusername.pythonanywhere.com/admin/
   ```
   (Login with the superuser you created)

3. **API Endpoints:**
   - `POST /api/save_user/` - User registration
   - `POST /api/user_login/` - User login
   - `GET /api/get_stations/` - Get EV stations
   - `POST /api/save_vehical/` - Add vehicle
   - `GET /api/get_battery_status/` - Battery status

### 10.2 Test with Postman or Browser

**Example (using browser):**
Visit: `https://yourusername.pythonanywhere.com/api/get_stations/`

You should get a JSON response (might be empty array `[]` if no stations in database).

---

## 🔧 Troubleshooting

### Error: "Something went wrong :("

**Solution:**
1. Go to **Web** tab
2. Click **"Error log"** link
3. Check the last few lines for the error
4. Common issues:
   - Wrong database credentials in settings.py
   - Virtual environment path incorrect
   - WSGI file has wrong username

### Error: "ImportError: No module named..."

**Solution:**
```bash
# In Bash console
workon smartev_env
cd ~/ev_app
pip install -r requirements.txt
```

Then reload the web app.

### Static Files Not Loading

**Solution:**
1. Check static files mapping in **Web** tab
2. Run in Bash:
   ```bash
   cd ~/ev_app
   python manage.py collectstatic --noinput
   ```
3. Reload web app

### Database Connection Error

**Solution:**
1. Verify database exists in **Databases** tab
2. Check credentials in `settings.py` match exactly
3. Ensure you replaced all instances of `yourusername`

---

## 🔄 Updating Your App

When you make changes to your code:

```bash
# In Bash console
cd ~/ev_app

# Pull latest changes from GitHub
git pull origin main

# Activate virtual environment
workon smartev_env

# Install any new dependencies
pip install -r requirements.txt

# Run migrations if models changed
python manage.py migrate

# Collect static files
python manage.py collectstatic --noinput
```

Then go to **Web** tab and click **"Reload"**.

---

## 📊 Free Tier Limits

PythonAnywhere free tier includes:

- ✅ **Always on** (no sleeping!)
- ✅ **512 MB disk space**
- ✅ **100,000 hits per day**
- ✅ **1 web app**
- ✅ **MySQL database** (up to 512 MB)
- ⚠️ **Custom domain requires paid plan** ($5/month)
- ⚠️ **Outbound HTTPS limited** (but your API works fine)

**This is MORE than enough for development and testing!**

---

## 🎯 Next Steps

1. ✅ **Test all API endpoints** with Postman
2. ✅ **Add some data** via the admin panel
3. ✅ **Share your API URL** with others
4. ✅ **Monitor usage** in PythonAnywhere dashboard

---

## 📝 Important URLs

- **Your Website:** `https://yourusername.pythonanywhere.com`
- **Admin Panel:** `https://yourusername.pythonanywhere.com/admin/`
- **PythonAnywhere Dashboard:** `https://www.pythonanywhere.com/user/yourusername/`
- **Error Logs:** Web tab → "Error log" link
- **Server Logs:** Web tab → "Server log" link

---

## 🆘 Need Help?

If you encounter any issues:
1. Check the **Error log** in Web tab
2. Review this guide step-by-step
3. PythonAnywhere forums: https://www.pythonanywhere.com/forums/
4. Ask me! I'm here to help 😊

---

## 🎉 Congratulations!

Your Smart EV Application is now:
- ✅ **Live on the internet**
- ✅ **Free forever**
- ✅ **Accessible from anywhere**
- ✅ **Professional and reliable**

**Share your API:** `https://yourusername.pythonanywhere.com`

Good luck with your project! 🚀
