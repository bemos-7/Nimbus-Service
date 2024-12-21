import os

from app.models.v1.file import FileNameSize


async def list_files(self, user_folder: str) -> list:
    user_dir = self._get_user_dir(user_folder)
    files = os.listdir(user_dir)
    if not files:
        return []

    return [
        FileNameSize(name=file, size_mb=round(os.path.getsize(os.path.join(user_dir, file)) / (1024 * 1024), 2))
        for file in files
    ]
