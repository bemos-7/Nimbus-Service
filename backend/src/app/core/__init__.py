import os

from app.core.local_file_manager import LocalFileManagerConfig

local_file_manager_config = LocalFileManagerConfig(upload_dir=os.environ["UPLOAD_DIR_URL"])
