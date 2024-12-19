import os

from .v1.get_file import get_file
from .v1.list_files import list_files
from .v1.save_file import save_file


class LocalFileManager:
    def __init__(self, upload_dir: str):
        self.upload_dir = upload_dir
        os.makedirs(self.upload_dir, exist_ok=True)

    def _get_user_dir(self, user_folder: str) -> str:
        user_dir = os.path.join(self.upload_dir, user_folder)
        if not os.path.exists(user_dir):
            os.makedirs(user_dir, exist_ok=True)
        return user_dir

    get_file = get_file
    list_files = list_files
    save_file = save_file
