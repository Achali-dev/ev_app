# 🚀 PythonAnywhere Quick Start Checklist

## Before You Start
- [ ] Have a GitHub account with code pushed ✅ (Done!)
- [ ] Choose a PythonAnywhere username (will be in your URL)

## Deployment Steps

### 1️⃣ Sign Up (5 mins)
- [ ] Go to: https://www.pythonanywhere.com/registration/register/beginner/
- [ ] Create account (no credit card needed!)
- [ ] Verify email
- [ ] Log in to Dashboard

### 2️⃣ Clone Repository (2 mins)
- [ ] Open **Bash console** from Dashboard
- [ ] Run: `git clone https://github.com/Achali-dev/ev_app.git`
- [ ] Run: `cd ev_app`

### 3️⃣ Create Virtual Environment (3 mins)
- [ ] Run: `mkvirtualenv --python=/usr/bin/python3.10 smartev_env`
- [ ] Run: `pip install -r requirements.txt`

### 4️⃣ Set Up MySQL Database (3 mins)
- [ ] Go to **Databases** tab
- [ ] Set MySQL password (write it down!)
- [ ] Create database: `smartevdb`
- [ ] Note: Database name will be `yourusername$smartevdb`

### 5️⃣ Configure Settings (5 mins)
- [ ] In Bash: `nano ~/ev_app/Smart_EV/settings.py`
- [ ] Update DATABASES section with MySQL credentials
- [ ] Update ALLOWED_HOSTS: `['yourusername.pythonanywhere.com', ...]`
- [ ] Save: Ctrl+X, Y, Enter

### 6️⃣ Run Django Commands (5 mins)
- [ ] Run: `python manage.py migrate`
- [ ] Run: `python manage.py collectstatic --noinput`
- [ ] Run: `python manage.py createsuperuser`
- [ ] Remember your admin credentials!

### 7️⃣ Create Web App (5 mins)
- [ ] Go to **Web** tab
- [ ] Click **Add a new web app**
- [ ] Choose **Manual configuration** → **Python 3.10**
- [ ] Set Virtualenv: `/home/yourusername/.virtualenvs/smartev_env`
- [ ] Edit WSGI file (copy from guide)
- [ ] Replace `yourusername` with your actual username

### 8️⃣ Configure Static Files (2 mins)
- [ ] In **Web** tab, under Static files:
  - [ ] `/static/` → `/home/yourusername/ev_app/staticfiles`
  - [ ] `/media/` → `/home/yourusername/ev_app/media`

### 9️⃣ Launch! (1 min)
- [ ] Click **Reload** button (green button at top)
- [ ] Wait ~10 seconds
- [ ] Visit: `https://yourusername.pythonanywhere.com`
- [ ] Should see: `{"status": "OK", "message": "Smart EV Server is running correctly."}`

### 🔟 Test Everything
- [ ] Test: `https://yourusername.pythonanywhere.com/admin/`
- [ ] Login with superuser credentials
- [ ] Test API endpoints
- [ ] Add some data via admin panel

---

## ⚡ Quick Commands Reference

```bash
# Navigate to project
cd ~/ev_app

# Activate environment
workon smartev_env

# Pull updates from GitHub
git pull origin main

# Install dependencies
pip install -r requirements.txt

# Run migrations
python manage.py migrate

# Collect static files
python manage.py collectstatic --noinput

# Create superuser
python manage.py createsuperuser
```

After making changes, always **Reload** your web app in the Web tab!

---

## 🚨 Common Issues

| Problem | Solution |
|---------|----------|
| Error 404 or 500 | Check Error log in Web tab |
| Can't login to admin | Run `python manage.py createsuperuser` again |
| Database error | Verify credentials in settings.py |
| Module not found | Run `pip install -r requirements.txt` in virtualenv |
| Changes not showing | Click Reload button in Web tab |

---

## 📍 Your Important URLs

Replace `yourusername` with your actual username:

- **Dashboard:** `https://www.pythonanywhere.com/user/yourusername/`
- **Website:** `https://yourusername.pythonanywhere.com`
- **Admin:** `https://yourusername.pythonanywhere.com/admin/`

---

## 🎯 Total Time: ~30 minutes

Read full guide: **PYTHONANYWHERE_DEPLOYMENT.md**

---

**Ready? Let's deploy! 🚀**
