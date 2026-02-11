import subprocess
import sys
import time
import os

# 1. Define the path to your Virtual Environment Python
# We use the specific python.exe in your .venv so we don't need to "activate" it manually
venv_python = os.path.join(os.getcwd(), ".venv", "Scripts", "python.exe")

# 2. Define your services
# tuple format: (folder_name, app_file:app_instance, port)
services = [
    # Chatbot Service -> Runs on Port 8001
    {
        "name": "Chatbot",
        "cwd": "movie-chatbot", 
        "command": [venv_python, "-m", "uvicorn", "main:app", "--port", "8001", "--reload"]
    },
    # Recommendation Service -> Runs on Port 8002
    {
        "name": "Recommendation",
        "cwd": "movie-recommendation",
        "command": [venv_python, "-m", "uvicorn", "main:app", "--port", "8002", "--reload"]
    }
]

processes = []

try:
    print(f"🚀 Starting Services using Python at: {venv_python}\n")
    
    for service in services:
        # Check if the folder exists to avoid errors
        if not os.path.exists(service["cwd"]):
            print(f"❌ Error: Folder '{service['cwd']}' not found. Check your directory structure.")
            continue

        print(f"   -> Launching {service['name']} on port {service['command'][6]}...")
        
        # Start the process inside its own folder (cwd) so imports work correctly
        p = subprocess.Popen(
            service["command"], 
            cwd=service["cwd"] # This sets the "working directory" to the subfolder
        )
        processes.append(p)

    print("\n✅ All services running. Press Ctrl+C to stop.")
    
    # Keep script alive
    while True:
        time.sleep(1)

except KeyboardInterrupt:
    print("\n🛑 Stopping all services...")
    for p in processes:
        p.terminate()