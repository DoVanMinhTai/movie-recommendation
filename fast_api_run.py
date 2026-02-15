import subprocess
import time
import os

# Danh sách các service
services = [
    {
        "name": "Chatbot",
        "cwd": "movie-chatbot", 
        "port": "8001"
    },
    {
        "name": "Recommendation",
        "cwd": "movie-recommendation",
        "port": "8002"
    }
]

processes = []

try:
    for service in services:
        cwd_path = os.path.join(os.getcwd(), service["cwd"])
        
        if not os.path.exists(cwd_path):
            print(f"❌ Không tìm thấy thư mục: {service['cwd']}")
            continue

        # Tự động tìm đường dẫn venv bên trong mỗi service
        # Windows: .venv\Scripts\python.exe | Linux/Mac: .venv/bin/python
        venv_path = os.path.join(cwd_path, ".venv", "Scripts", "python.exe")
        
        # Lệnh chạy uvicorn
        cmd = [
    "uvicorn", "main:app", 
    "--port", service["port"], 
    "--reload"
        ]
        # Và thêm shell=True trong Popen
        p = subprocess.Popen(cmd, cwd=cwd_path, shell=True)
        
        processes.append(p)

    print("\n✅ Tất cả service đã khởi động. Nhấn Ctrl+C để dừng.")
    
    while True:
        time.sleep(1)

except KeyboardInterrupt:
    print("\n🛑 Đang dừng các service...")
    for p in processes:
        p.terminate()