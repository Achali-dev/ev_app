# 🚂 Railway Deployment Guide - Smart EV App

## Current Issue Analysis

If Railway is still failing to build, follow these manual configuration steps:

---

## 🔧 Railway Dashboard Configuration

### Step 1: Add PostgreSQL Database

1. In your Railway project, click **"+ New"**
2. Select **"Database"** → **"PostgreSQL"**
3. Railway will create a database and provide a `DATABASE_URL`

### Step 2: Configure Environment Variables

Go to your `smart-ev-backend` service → **Settings** → **Environment Variables**

Add these variables:

| Variable Name | Value |
|---------------|-------|
| `DATABASE_URL` | (Copy from PostgreSQL service) |
| `SECRET_KEY` | `django-insecure-14cg5v%z8qe75=)7pnuinh%nhz_cm5_$gbjop35$3t%vys)qk5` |
| `DEBUG` | `False` |
| `EMAIL_HOST_USER` | `vinay.agiletec@gmail.com` |
| `EMAIL_HOST_PASSWORD` | `twvfkoilmndvnevn` |
| `PYTHON_VERSION` | `3.11.0` |
| `DISABLE_COLLECTSTATIC` | `0` |

### Step 3: Configure Build Settings

In Railway → **Settings**:

**Build Command:**
```bash
pip install -r requirements.txt && python manage.py collectstatic --noinput
```

**Start Command:**
```bash
python manage.py migrate --noinput && gunicorn Smart_EV.wsgi:application --bind 0.0.0.0:$PORT
```

**Or use the Procfile** (already configured):
- Railway should auto-detect the `Procfile`
- Leave Start Command empty to use Procfile

### Step 4: Manual Deployment Settings (Alternative)

If automatic deployment keeps failing, try these Railway CLI commands:

```bash
# Install Railway CLI
npm i -g @railway/cli

# Login
railway login

# Link to your project
railway link

# Deploy manually
railway up
```

---

## 🐛 Troubleshooting Railway Errors

### Error: "ModuleNotFoundError" or Import Errors

**Solution 1:** Ensure `requirements.txt` has all dependencies

**Solution 2:** In Railway Settings → Build, try this build command:
```bash
pip install --upgrade pip setuptools wheel && pip install -r requirements.txt && python manage.py collectstatic --noinput
```

### Error: "Connection refused" or Database errors

**Solution:** 
1. Ensure PostgreSQL service is created
2. Link DATABASE_URL environment variable correctly
3. Format should be: `postgresql://user:password@host:port/database`

### Error: Build timeout

**Solution:**
1. Remove `nixpacks.toml` temporarily
2. Let Railway auto-detect Python
3. Manually set Build and Start commands in Settings

---

## 🎯 Alternative: Try PythonAnywhere (100% Free)

If Railway continues to have issues, I recommend **PythonAnywhere** instead:

### Why PythonAnywhere?
- ✅ **100% Free** (no credit card required)
- ✅ **Django-specific** hosting
- ✅ **Simpler deployment** process
- ✅ **No build failures** like Railway
- ✅ **Always online** (no cold starts)

### Quick PythonAnywhere Setup:

1. Sign up at: https://www.pythonanywhere.com/registration/register/beginner/
2. Create a new **Flask/Django** web app
3. Upload your code via Git or Files tab
4. Configure MySQL database (free tier includes MySQL)
5. Set up virtualenv and install requirements
6. Configure WSGI file
7. Your app will be at: `yourusername.pythonanywhere.com`

**Would you like me to create a detailed PythonAnywhere deployment guide?**

---

## 🆘 Quick Fix for Railway

### Remove Complex Build Configuration

Try removing these files temporarily and let Railway auto-detect:

```bash
git rm nixpacks.toml
git rm runtime.txt
git commit -m "Remove custom build config"
git push
```

Then in Railway Dashboard manually set:
- **Build:** `pip install -r requirements.txt`
- **Start:** Check the Procfile

---

## 💡 Recommendation

Based on the persistent build failures on Railway, I suggest:

**Option 1:** Try the manual Railway configuration above

**Option 2:** Switch to **PythonAnywhere** (easier, free, Django-focused)

**Option 3:** Use **Render** with credit card (original plan, most reliable)

Which would you like to proceed with?
