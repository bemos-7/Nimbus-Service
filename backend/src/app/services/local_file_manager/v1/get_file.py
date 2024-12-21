import os

from fastapi import HTTPException


async def get_file(self, user_folder: str, filename: str) -> tuple[str, int]:
    user_dir = self._get_user_dir(user_folder)
    try:
        file_path = os.path.join(user_dir, filename)
        if not os.path.exists(file_path):
            raise HTTPException(status_code=404, detail="Файл не найден")

        file_size = os.path.getsize(file_path)
        return file_path, file_size
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Ошибка чтения файла: {e}")
