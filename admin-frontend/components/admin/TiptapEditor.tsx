'use client';

import { EditorContent, useEditor } from '@tiptap/react';
import StarterKit from '@tiptap/starter-kit';
import { useEffect } from 'react';

type Props = {
    value: string;
    onChange: (value: string) => void;
};

export default function TiptapEditor({ value, onChange }: Props) {
    const editor = useEditor({
        extensions: [StarterKit],
        content: value || '',
        immediatelyRender: false,
        onUpdate: ({ editor }) => {
            onChange(editor.getHTML());
        },
        editorProps: {
            attributes: {
                class: 'tiptap-editor-content',
            },
        },
    });

    useEffect(() => {
        if (!editor) return;

        const currentHtml = editor.getHTML();

        if (currentHtml !== (value || '')) {
            editor.commands.setContent(value || '', {
                emitUpdate: false,
            });
        }
    }, [editor, value]);

    if (!editor) {
        return null;
    }

    return (
        <div className="tiptap-editor-wrapper">
            <div className="tiptap-toolbar">
                <button
                    type="button"
                    className={`btn btn-sm ${editor.isActive('bold') ? 'btn-dark' : 'btn-outline-dark'}`}
                    onClick={() => editor.chain().focus().toggleBold().run()}
                >
                    Bold
                </button>

                <button
                    type="button"
                    className={`btn btn-sm ${editor.isActive('italic') ? 'btn-dark' : 'btn-outline-dark'}`}
                    onClick={() => editor.chain().focus().toggleItalic().run()}
                >
                    Italic
                </button>

                <button
                    type="button"
                    className={`btn btn-sm ${editor.isActive('bulletList') ? 'btn-dark' : 'btn-outline-dark'}`}
                    onClick={() => editor.chain().focus().toggleBulletList().run()}
                >
                    Bullet
                </button>

                <button
                    type="button"
                    className={`btn btn-sm ${editor.isActive('orderedList') ? 'btn-dark' : 'btn-outline-dark'}`}
                    onClick={() => editor.chain().focus().toggleOrderedList().run()}
                >
                    Number
                </button>

                <button
                    type="button"
                    className={`btn btn-sm ${editor.isActive('heading', { level: 2 }) ? 'btn-dark' : 'btn-outline-dark'}`}
                    onClick={() => editor.chain().focus().toggleHeading({ level: 2 }).run()}
                >
                    H2
                </button>

                <button
                    type="button"
                    className="btn btn-sm btn-outline-secondary"
                    onClick={() => editor.chain().focus().setParagraph().run()}
                >
                    Paragraph
                </button>
            </div>

            <EditorContent editor={editor} />
        </div>
    );
}