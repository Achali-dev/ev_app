#!/bin/bash
# Railway build script

echo "🚀 Installing dependencies..."
pip install --upgrade pip setuptools wheel
pip install -r requirements.txt

echo "📦 Collecting static files..."
python manage.py collectstatic --noinput --clear

echo "✅ Build complete!"
